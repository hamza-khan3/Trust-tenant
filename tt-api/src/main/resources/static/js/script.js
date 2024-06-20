

function signIn() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const request = {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: username,
            password: password
        })
    }

    fetch("/session", request).then(
        (response) => {
            if(response.status === 401) {
                alert("Invalid username or password!");

            } else if(response.status === 201) {
                hideSignIn();
                document.getElementById('main-content').style.display = 'block';
                document.getElementById('logged-in-content').style.display = 'block';
            }
        }, () => {
            alert("An unknown error occurred!");
        }
    );
}
function showRegistrationForm() {
    document.getElementById('signin-modal').style.display = 'none';
    document.getElementById('registration-form').style.display = 'block';
}

function hideRegistrationForm() {
    document.getElementById('registration-form').style.display = 'none';
    document.getElementById('signin-modal').style.display = 'block';
}

function register() {
    var username = document.getElementById('reg-username').value;
    var password = document.getElementById('reg-password').value;
    var confirmPassword = document.getElementById('reg-confirm-password').value;
    var firstName = document.getElementById('reg-firstname').value;
    var lastName = document.getElementById('reg-lastname').value;

    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return;
    }

    // Build the request
    const request = {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: username, // Assuming username is the email
            password: password,
            fName: firstName,
            lName: lastName
        })
    };

    // Send the request to the backend
    fetch("/register", request)
        .then(response => {
            if (response.status === 201) {
                alert("Registration successful!");

                // Hide the registration form
                document.getElementById('registration-form').style.display = 'none';

                // Display main page for logged in user
                document.getElementById('main-content').style.display = 'block';
                document.getElementById('logged-in-content').style.display = 'block';

            } else if (response.status === 409) {
                response.text().then(
                    (message) => {
                        alert(message);
                    },
                    () => {
                        alert("Registration failed. Status code: " + response.status);
                    }
                )
            } else {
                alert("Registration failed. Status code: " + response.status);
            }
        })
        .catch(error => {
            console.error("Error during registration:", error);
        });
}

function checkForSession() {

}

function hideSignIn() {
    document.getElementById('signin-modal').style.display = 'none';
    document.getElementById('username').value = null;
    document.getElementById('password').value = null;
}

function continueWithoutSigningIn() {
    hideSignIn();
    document.getElementById('main-content').style.display = 'block';
    document.getElementById('guest-content').style.display = 'block';
}

function backToLogin() {
    document.getElementById('main-content').style.display = 'none';
    document.getElementById('guest-content').style.display = 'none';
    document.getElementById('signin-modal').style.display = 'block';
    document.getElementById('rental-list').replaceChildren(
        'Press search to view available rentals');
}


function logout() {

    const token = document.cookie
        .split("; ")
        .find((row) => row.startsWith("trustToken="))
        ?.split("=")[1];

    const request = {
        method: "DELETE",
        body: token
    }

    fetch("/session", request).then(
        (response) => {
            if(response.status === 200) {
                alert("Logged out");

            } else if(response.status === 204) {
                alert("You weren't logged in to begin with.");
            }
        },
        () => {
            alert("Something happened");
        }
    );

    document.getElementById('main-content').style.display = 'none';
    document.getElementById('logged-in-content').style.display = 'none';
    document.getElementById('signin-modal').style.display = 'block';
    document.getElementById('rental-list').replaceChildren(
        'Press search to view available rentals');
}

function showRentalForm() {
    document.getElementById('rental-form').style.display = 'block';
}

function searchRentals() {
    //Build attributes list
    let attributes = [];
    if(document.getElementById('utilities-included').value === 'yes') {
        attributes.push('utilities-included');
    }
    if(document.getElementById('furnished').value === 'yes') {
        attributes.push('furnished');
    }
    if(document.getElementById('smoking').value === 'yes') {
        attributes.push('smoking');
    }
    if(document.getElementById('pet-friendly').value === 'yes') {
        attributes.push('pet-friendly');
    }

    const type = document.getElementById('property-type').value;
    const minRent = document.getElementById('min-rent').value;
    const maxRent = document.getElementById('max-rent').value;
    const minBedrooms = document.getElementById('bedrooms').value;
    const minBathrooms = document.getElementById('bathrooms').value;

    const request = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            type: type,
            rentMin: minRent,
            rentMax: maxRent,
            minBedrooms: minBedrooms,
            minBathrooms: minBathrooms,
            descriptors: attributes
        })
    }

    fetch('/properties/filter', request)
        .then((response) => response.json())
        .then(
            (properties) => {
                if(!Array.isArray(properties) || !properties.length) {
                    document.getElementById('rental-list')
                        .replaceChildren("No Results");
                    return;
                }

                document.getElementById('rental-list')
                    .replaceWith(buildPropertyList(properties));
            },
            () => {
                alert("No good.");
            }
        );
}

function buildPropertyList(properties) {
    let listDiv = document.createElement('div');
    listDiv.setAttribute('id', 'rental-list');

    let property;
    let propertyId;
    for(property of properties) {
        propertyId = property['propertyId'];

        let propertyAnchor = document.createElement('a');
        propertyAnchor.setAttribute('href', '/properties/property/' + propertyId + '/view');

        let propertyDiv = document.createElement('div');
        propertyDiv.setAttribute('class', 'list-property')
        propertyDiv.setAttribute('onclick', 'viewProperty(' + propertyId + ')')

        let elemText = document.createElement('pre');
        elemText.append(property['type'] + '    ID ' + propertyId +
            "\nAddress: " + property['address'])

        propertyDiv.appendChild(elemText);
        propertyAnchor.append(propertyDiv);
        listDiv.append(propertyAnchor);
    }
    return listDiv;
}

function addRental() {
    var title = document.getElementById('title').value;
    var description = document.getElementById('description').value;
    var price = document.getElementById('price').value;

    var rentalList = document.getElementById('rental-list');

    var rentalDiv = document.createElement('div');
    rentalDiv.classList.add('rental-item');
    rentalDiv.innerHTML = '<h3>' + title + '</h3><p>' + description + '</p><p>Price: $' + price + '</p>';

    rentalList.appendChild(rentalDiv);


    document.getElementById('title').value = '';
    document.getElementById('description').value = '';
    document.getElementById('price').value = '';
    document.getElementById('rental-form').style.display = 'none';
}

function saveProperty() {
    alert("Can't save property");
}

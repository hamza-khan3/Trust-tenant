USE TRUST_TENANT;

INSERT INTO USER(Email, Password, FName, LName, Role) VALUES
    ('user@cpsc.com', 'CP$C471', 'Person', 'McMan', 'USER'),
	('mod@trustmail.ca', 'AverageM0d', 'Rod', 'Tod', 'MOD'),
	('his-lordship@business.com', 'IH8P00RPEOPLELOL', 'Cash', 'Haver', 'LORD'),
	('admin@trustmail.ca', 'MadMin99', 'Adam', 'Ino', 'ADMIN');

INSERT INTO COMMUNITY(Name) VALUES
    ('Community');

INSERT INTO PROPERTY(Address, Type, OwnerId, CommunityId) VALUES
    ('1 address street', 'basement', 3, 1),
	('3 address street', 'apartment', 3, 1),
	('4 address street', 'townhouse', 3, 1);

INSERT INTO LISTING(Propertyid, Type, Numbeds, Numbaths, Sqrfootage, Rent, Status) VALUES
    ('1', '1 Bedroom Basement Suite', 1, 1, 800, 1200, 'ACTIVE'),
    ('2', 'Studio Apartment', 1, 1, 400, 800, 'ACTIVE'),
    ('2', '2 Bed 1 Bath Apartment', 2, 1, 700, 1600, 'ACTIVE'),
    ('3', '3 Bedroom Townhouse', 3, 2.5, 1600, 3000, 'ACTIVE');

INSERT INTO LISTING_DESC(PropertyId, ListingNum, Descriptor) VALUES
    (1, 1, 'smoking'),
    (1, 1, 'pet-friendly'),
    (1, 1, 'furnished'),
    (2, 2, 'furnished'),
    (2, 3, 'furnished'),
    (3, 4, 'pet-friendly'),
    (3, 4, 'smoking');


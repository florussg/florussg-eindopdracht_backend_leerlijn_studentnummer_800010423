INSERT INTO customer (firstname, lastname, bsn_number, phone_number)
VALUES ('Piet', 'Florus', 111111111, 0654545454),
       ('Johan', 'Pieterse', 222222222, 0612457845),
       ('Peter', 'van Gisteren', 333333333, 0625825836),
       ('Truusje', 'Florus', 145145145, 0687548754);

INSERT INTO car (brand, type, licenseplate_number)
VALUES ('Seat', 'Leon', '31-LZ-XL'),
       ('Toyota', 'Yaris', 'GF-119-G');

INSERT INTO appointment (appointment_date_time, appointment_apk, appointment_repair)
VALUES ('2022-03-02T13:26:00', true, false),
       ('2022-03-25T13:00:00', false, true),
       ('2022-04-01T09:00:00', true, false),
       ('2022-04-01T11:00:00', false, true);

INSERT INTO part (description, price, brand_type_year)
VALUES ('Remschijf voor', 45, 'Toyota Yaris 2001'),
       ('Remschijf achter', 45, 'Toyota Yaris 2001'),
       ('Koppelingsplaat', 700, 'Seat Leon 2015'),
       ('Motorblok ECO', 1500, 'Seat Leon 2015'),
       ('Wiellager', 80, 'Mazda CX5 2020'),
       ('Ruitenwisser', 10, 'Mazda CX5 2020');

INSERT INTO repair (finding, repair_status, repair_appointment_id)
VALUES ('APK failed want remschijven voorzijde onder de minimale eis', 2, 2),
       ('APK failed want band links achter onder de minimale eis', 1,4);





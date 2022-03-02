INSERT INTO customer (firstname, lastname, bsn_number, phone_number)
VALUES ('Piet', 'Florus', 111111111, 0654545454),
       ('Johan', 'Pieterse', 222222222, 0612457845),
       ('Peter', 'van Gisteren', 333333333, 0625825836),
       ('Truusje', 'Florus', 145145145, 0687548754);

INSERT INTO car (brand, type, licenseplate_number, registration_document)
VALUES ('Seat', 'Leon', '31-LZ-XL', 'kentekenpapieren.pdf'),
       ('Toyota', 'Yaris', 'GF-119-G', 'kentekenpapierenYaris.pdf');

INSERT INTO appointment (appointment_date_time, appointment_apk, appointment_repair)
VALUES ('2022-03-02T13:26:00', true, false),
       ('2022-03-25T13:00:00', false, true),
       ('2022-04-01T09:00:00', true, false);
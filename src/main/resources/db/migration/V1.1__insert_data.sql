INSERT INTO customer (`name`,`address`,`email`,`phone_number`) VALUES
    ('Ayrton Senna', 'Avenida Brasil, 1', 'ayrton.senna@xyz.com', '+55 5555-1212'),
    ('Alain Prost', 'Rua Paris, 2', 'alain.prost@xyz.com', '+33 5555-7272'),
    ('Nelson Piquet', 'Rua xpto, 3', 'nelson.piquet@xyz.com', '5555-3333'),
    ('Niki Lauda', 'Rua xpto, 4', 'niki.lauda@xyz.com', '5555-4444');

INSERT INTO channel (`number`,`name`) VALUES
    ('100', 'Sports'),
    ('500', 'Sports HD'),
    ('101', 'News'),
    ('501', 'News HD'),
    ('102', 'Movies'),
    ('502', 'Movies HD');

INSERT INTO customer_channel (channel_id, customer_id)
    SELECT channel.id,customer.id
    FROM channel
    LEFT JOIN customer;

UPDATE customer_channel
SET subscribed = 1
WHERE customer_id in (1,3);

UPDATE customer_channel
SET subscribed = 1
WHERE customer_id in (2,4) and channel_id in (select id from channel where number < 500);

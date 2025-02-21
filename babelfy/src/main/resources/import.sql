INSERT IGNORE INTO category (id,name) VALUES (1,'Rock');
INSERT IGNORE INTO category (id,name) VALUES (2,'Pop');
INSERT IGNORE INTO category (id,name) VALUES (3,'Hip Hop');

INSERT IGNORE INTO song (date, duration, category_id, album, artist, name)
VALUES ('2025-2-1', 5, 1, 'People Watching', 'Sam Fender', 'People Watching');

INSERT IGNORE INTO song (date, duration, category_id, album, artist, name)
VALUES ('2024-5-17', 3, 2, 'So Clase To Watch', 'Tata McRae', 'Revolving door');

INSERT IGNORE INTO song (date, duration, category_id, album, artist, name)
VALUES ('2024-11-23', 4, 3, 'Some Sexy Songs 4 U', 'Drake', 'Nokia');
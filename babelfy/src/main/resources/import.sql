INSERT IGNORE INTO category (id, name) VALUES (1, 'Rock');
INSERT IGNORE INTO song (date, duration, category_id, album, artist, name) VALUES
('2025-01-01', 4, 1, 'Echoes of Thunder', 'The Wild Chords', 'Raging Storm'),
('2025-01-15', 5, 1, 'Electric Dreams', 'Fire Strings', 'High Voltage'),
('2025-02-10', 4, 1, 'Rock & Roll Saga', 'Stone Rebels', 'Breaking Chains'),
('2025-03-05', 4, 1, 'Metal Heart', 'Iron Pulse', 'Thunderstrike'),
('2025-04-20', 3, 1, 'Echoed Memories', 'Shadow Echo', 'Lost in Time'),
('2025-05-15', 5, 1, 'Rebel Souls', 'Midnight Howlers', 'Whispered Screams'),
('2025-06-30', 4, 1, 'Wild Symphony', 'Rogue Waves', 'Roaring Oceans'),
('2025-07-14', 4, 1, 'Eternal Flames', 'Phoenix Rise', 'Ashes and Dust'),
('2025-08-01', 4, 1, 'Mystic Trails', 'Echo Rangers', 'Shadow Dancer'),
('2025-09-09', 3, 1, 'Blazing Horizons', 'Fire Trails', 'Sunset Riders'),
('2025-10-25', 5, 1, 'Electric Pulse', 'Voltage Kings', 'High on Power'),
('2025-11-11', 4, 1, 'Stone Legends', 'Granite Beats', 'Rock Solid'),
('2025-12-01', 4, 1, 'Metal Echoes', 'Iron Shadows', 'Steel Dreams'),
('2025-12-15', 4, 1, 'Roaring Waves', 'Oceanic Fury', 'Tempest Calls'),
('2025-12-30', 5, 1, 'Whispered Echoes', 'Silent Roar', 'Echoing Thunder');
INSERT IGNORE INTO category (id, name) VALUES (2, 'Pop');
INSERT IGNORE INTO song (date, duration, category_id, album, artist, name) VALUES
('2025-01-11', 3, 2, 'Dancing Lights', 'Luna Sparks', 'Starlit Dreams'),
('2025-02-18', 4, 2, 'Velvet Nights', 'Echoing Hearts', 'Midnight Dance'),
('2025-03-15', 3, 2, 'City Vibes', 'Urban Beats', 'Neon Streets'),
('2025-04-20', 4, 2, 'Golden Waves', 'Summer Echo', 'Sunset Glow'),
('2025-05-10', 3, 2, 'Electric Feelings', 'Pulse Drive', 'Heartbeats'),
('2025-06-25', 4, 2, 'Rhythmic Harmony', 'Echo Melody', 'Dancing Shadows'),
('2025-07-14', 3, 2, 'Sparkling Dreams', 'Neon Harmony', 'Electric Love'),
('2025-08-05', 4, 2, 'Pop Paradise', 'Echoing Stars', 'Shining Bright'),
('2025-09-09', 3, 2, 'Urban Rhythms', 'City Beats', 'Night Lights'),
('2025-10-02', 4, 2, 'Electro Groove', 'Pulse Waves', 'Electric Pulse'),
('2025-11-20', 3, 2, 'Midnight Echoes', 'Echo Dancers', 'Neon Glow'),
('2025-12-15', 4, 2, 'Vibrant Waves', 'Pulse Harmony', 'Colorful Beats'),
('2025-12-31', 3, 2, 'Starry Nights', 'Neon Echo', 'City Lights'),
('2025-11-29', 4, 2, 'Glowing Dreams', 'Echo Waves', 'Shimmering Nights'),
('2025-10-19', 4, 2, 'Dance Vibes', 'Urban Echo', 'Rhythmic Dreams');
INSERT IGNORE INTO category (id, name) VALUES (3, 'Hip Hop');
INSERT IGNORE INTO song (date, duration, category_id, album, artist, name) VALUES
('2025-01-03', 3, 3, 'Beats & Rhymes', 'MC Thunder', 'Pulse of the Street'),
('2025-02-14', 4, 3, 'Urban Echoes', 'Flow Masters', 'City Lights'),
('2025-03-22', 4, 3, 'Beats Unleashed', 'Echo Rhyme', 'Bass Drop'),
('2025-04-17', 3, 3, 'Rhythmic Flow', 'Pulse Kings', 'Fast Lane'),
('2025-05-30', 4, 3, 'Echoed Beats', 'Urban Flow', 'Neon Vibes'),
('2025-06-24', 3, 3, 'Street Rhythms', 'Beats Echo', 'City Shadows'),
('2025-07-13', 4, 3, 'Flow Legends', 'Echo Kings', 'Breaking Silence'),
('2025-08-21', 3, 3, 'Pulse Waves', 'Rhyme Masters', 'Echoed Dreams'),
('2025-09-02', 4, 3, 'Urban Pulse', 'City Beats', 'Midnight Flow'),
('2025-10-14', 3, 3, 'Echoes of the Street', 'Pulse Echo', 'Urban Shadows'),
('2025-11-11', 4, 3, 'Beats of Life', 'Flow Riders', 'Echoed Rhythms'),
('2025-12-06', 3, 3, 'Rhyme Echoes', 'Urban Pulse', 'Night Vibes'),
('2025-12-28', 4, 3, 'Pulse Rhythms', 'Echo Kings', 'Electric Flow'),
('2025-10-19', 4, 3, 'Street Echo', 'Rhyme Echo', 'City Dreams'),
('2025-11-22', 3, 3, 'Urban Legends', 'Echo Flow', 'Bass Bounce');
INSERT IGNORE INTO category (id,name) VALUES (500, 'Sin categoría');
INSERT IGNORE INTO category (id, name) VALUES (5, 'Jazz');
INSERT IGNORE INTO song (date, duration, category_id, album, artist, name) VALUES
('2025-02-08', 5, 5, 'Smooth Nights', 'The Jazz Quartet', 'Midnight Serenade'),
('2025-04-14', 4, 5, 'Blue Echoes', 'Sax Masters', 'Whispering Breeze'),
('2025-06-01', 6, 5, 'Cool Jazz', 'Ella Harmony', 'Velvet Moon');
INSERT IGNORE INTO category (id, name) VALUES (6, 'Electrónica');
INSERT IGNORE INTO song (date, duration, category_id, album, artist, name) VALUES
('2025-01-20', 4, 6, 'Neon Dreams', 'Synth Waves', 'Electric Pulse'),
('2025-03-30', 5, 6, 'Bass Boost', 'DJ Echo', 'Digital Vibes'),
('2025-07-18', 4, 6, 'Cyber Beats', 'Pulse Driver', 'Virtual Groove');
INSERT IGNORE INTO category (id, name) VALUES (7, 'Indie');
INSERT IGNORE INTO song (date, duration, category_id, album, artist, name) VALUES
('2025-02-28', 4, 7, 'Echoed Stories', 'Whispering Souls', 'Lost in Echoes'),
('2025-05-17', 3, 7, 'Urban Poets', 'The Lonely Hearts', 'Midnight Stroll'),
('2025-09-11', 4, 7, 'Dreamscapes', 'Echoing Silence', 'Fading Lights');
INSERT IGNORE INTO category (id, name) VALUES (8, 'Clásica');
INSERT IGNORE INTO song (date, duration, category_id, album, artist, name) VALUES
('2025-03-05', 6, 8, 'Timeless Echoes', 'Orquesta Sinfónica', 'Sinfonía de la Aurora'),
('2025-06-10', 5, 8, 'Melodías Eternas', 'Virtuosos del Piano', 'Nocturno en C'),
('2025-10-20', 7, 8, 'Clásicos Revisitados', 'Maestros del Violín', 'Oda a la Primavera');
INSERT IGNORE INTO category (id, name) VALUES (9, 'Metal');
INSERT IGNORE INTO song (date, duration, category_id, album, artist, name) VALUES
('2025-01-29', 5, 9, 'Fuerza Bruta', 'Steel Warriors', 'Grito del Infierno'),
('2025-04-04', 4, 9, 'Ecos del Abismo', 'Dark Shadows', 'Reino Sombrío'),
('2025-07-25', 6, 9, 'Ritual Metálico', 'Iron Fist', 'Forjando Destinos');
INSERT IGNORE INTO category (id, name) VALUES (10, 'Country');
INSERT IGNORE INTO song (date, duration, category_id, album, artist, name) VALUES
('2025-02-14', 4, 10, 'Caminos Polvorientos', 'The Western Souls', 'Corazón Vaquero'),
('2025-05-24', 3, 10, 'Historias del Rancho', 'Johnny Blue', 'Recuerdos del Oeste'),
('2025-08-19', 4, 10, 'Baladas del Sur', 'Willow Creek Band', 'Días de Campo');
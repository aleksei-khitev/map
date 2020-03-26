insert into ship(id, type, ship_class, producer, cost, autonomy, link) values(1, 'Корвет', 'СР90 Убийца', 'Corellian Engineering Corporation', 2500000, 365, 'http://starforge.info/rebellion-ships/cr90-corellian-corvette/');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(1, 140, 59, 17);
insert into ship_crew(ship_id, minimal, normal) values(1, 60, 84);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(1, 60, 950, 2, 8);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(1, 120, 880, 15, 138);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(1, 1, 6);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(1, 2, 2);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(1, 3, 1);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(1, 8, 1);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(1, 2, 6);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(1, 1, 60);

insert into ship(id, type, ship_class, producer, cost, autonomy, link, comment) values(2, 'Фрегат', 'Пиканосец', 'Kuat Drive Yards', 4760000, 7, 'http://starforge.info/galactic-empire-ships/lancer-class-frigate/', '<ul><li>20 орудий ПВО-направленности.</li><li>Может входить и биться в атмосфере</li></ul>');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(2, 250, null, null);
insert into ship_crew(ship_id, minimal, normal) values(2, 375, 850);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(2, 40, null, 2, 15);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(2, 125, 800, 15, 138);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(2, 5, 20);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(2, 1, 2);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(2, 1, 40);

insert into ship(id, type, ship_class, producer, cost, autonomy, link, comment) values(3, 'Эскортный фрегат', 'Небулон-Б II', 'Kuat Drive Yards', null, 1095, 'http://starforge.info/galactic-empire-ships/nebulon-b2-escort-frigate/', '<ul><li>40+ орудий включая турболазеры.</li><li>24 истребителя.</li><li>Может входить и биться в атмосфере</li></ul>');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(3, 250, null, null);
insert into ship_crew(ship_id, minimal, normal) values(3, 420, 1120);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(3, 40, 800, 2, 12);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(3, 130, 900, 20, 900);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(3, 6, 18);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(3, 7, 22);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(3, 8, 4);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(3, 1, 6);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(3, 2, 24);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(3, 1, 150);

insert into ship(id, type, ship_class, producer, cost, autonomy, link, comment) values(4, 'Патрульный крейсер', 'Тартан', 'Damorian Manufacturing Corporation', 4200000, 365, 'http://starforge.info/galactic-empire-ships/tartan-class-patrol-cruiser/', '<ul><li>Защита слабее, чем у каракки.</li><li>Видимо, не входит в атмосферу</li></ul>');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(4, 250, null, null);
insert into ship_crew(ship_id, minimal, normal) values(4, null, 70);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(4, 80, null, 2, 12);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(4, 90, 870, 15, 135);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(4, 9, 20);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(4, 1, 50);

insert into ship(id, type, ship_class, producer, cost, autonomy, link, comment) values(5, 'Лёгкий крейсер', 'Каракка', 'Damorian Manufacturing Corporation', 19148000, 365, 'http://starforge.info/galactic-empire-ships/carrack-class-light-cruiser/', '<ul><li>Скорость.</li><li>Может входить и биться в атмосфере.</li><li>30+ различных орудий</li></ul>');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(5, 350, 72, 75);
insert into ship_crew(ship_id, minimal, normal) values(5, 2, 1092);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(5, 80, 1050, 1, 12);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(5, 130, 1000, 15, 140);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(5, 10, 10);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(5, 8, 5);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(5, 11, 20);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(5, 1, 142);

insert into ship(id, type, ship_class, producer, cost, autonomy, link, comment) values(6, 'Лёгкий зенитный крейсер', 'Каракка (Зен)', 'Damorian Manufacturing Corporation', 19148000, 365, 'http://starforge.info/galactic-empire-ships/carrack-class-light-cruiser/', '<ul><li>Скорость.</li><li>Может входить и биться в атмосфере.</li><li>30 различных орудий</li></ul>');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(6, 350, 72, 75);
insert into ship_crew(ship_id, minimal, normal) values(6, 2, 1092);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(6, 80, 1050, 1, 12);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(6, 130, 1000, 15, 140);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(6, 10, 10);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(6, 7, 20);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(6, 11, 20);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(6, 1, 142);

insert into ship(id, type, ship_class, producer, cost, autonomy, link) values(7, 'Тяжёлый крейсер', 'Дредноут v1', 'Rendili StarDrive', 35555000, 730, 'http://starforge.info/galactic-empire-ships/dreadnaught-class-heavy-cruiser/');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(7, 600, 117, 129);
insert into ship_crew(ship_id, minimal, normal) values(7, 9000, 16000);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(7, 40, null, 2, 18);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(7, 115, 1680, 20, 253);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(7, 12, 10);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(7, 13, 20);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(7, 14, 10);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(7, 1, 1);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(7, 2, 12);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(7, 1, 3000);

insert into ship(id, type, ship_class, producer, cost, autonomy, link) values(8, 'Тяжёлый крейсер', 'Дредноут v2', 'Rendili StarDrive', 35555000, 730, 'http://starforge.info/galactic-empire-ships/dreadnaught-class-heavy-cruiser/');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(8, 600, 117, 129);
insert into ship_crew(ship_id, minimal, normal) values(8, 9000, 16000);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(8, 40, null, 2, 18);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(8, 115, 1680, 20, 253);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(8, 12, 15);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(8, 15, 15);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(8, 16, 20);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(8, 1, 1);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(8, 2, 12);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(8, 1, 3000);

insert into ship(id, type, ship_class, producer, cost, autonomy, link, comment) values(9, 'Тяжёлый крейсер', 'Мститель', 'Sienar Fleet Systems', 43000000, 547, 'http://starforge.info/galactic-empire-ships/vindicator-class-heavy-cruiser/', '<ul><li>Самодостаточный.</li><li>Может выполнять задачи в одиночку.</li><li>Эффективен и против малых судов</li></ul>');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(9, 600, null, null);
insert into ship_crew(ship_id, minimal, normal) values(9, null, 2551);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(9, 40, null, 2, 8);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(9, 150, 1800, 20, 254);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(9, 14, 25);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(9, 16, 20);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(9, 17, 20);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(9, 11, 25);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(9, 8, 3);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(9, 1, 4);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(9, 2, 24);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(9, 1, 400);

insert into ship(id, type, ship_class, producer, cost, autonomy, link, comment) values(10, 'Тяжёлый крейсер', 'Воспрещающий', 'Sienar Fleet Systems', 52240000, 365, 'http://starforge.info/galactic-empire-ships/interdictor-class-heavy-cruiser/', 'Колодцы');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(10, 600, 300, 100);
insert into ship_crew(ship_id, minimal, normal) values(10, 1500, 2807);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(10, 60, null, 2, 8);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(10, 150, 1500, 20, 252);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(10, 18, 20);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(10, 19, 4);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(10, 1, 4);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(10, 2, 24);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(10, 1, 80);

insert into ship(id, type, ship_class, producer, cost, autonomy, link, comment) values(11, 'Линейный крейсер/Авианосец', 'Секутор', 'Kuat Drive Yards', 200000000, 730, 'http://starforge.info/galactic-empire-ships/secutor-class-star-destroyer/', '<ul><li>144 истребителя.</li><li>Многочисленные челноки, десантные баржи и вспомогательные малые суда</li></ul>');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(11, 2200, null, null);
insert into ship_crew(ship_id, minimal, normal) values(11, null, 40000);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(11, null, null, 2, 14);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(11, null, null, null, null);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(11, 20, 15);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(11, 14, 15);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(11, 21, 15);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(11, 11, 16);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(11, 8, 12);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(11, 2, 144);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(11, 1, 14000);

insert into ship(id, type, ship_class, producer, cost, autonomy, link) values(12, 'Штурмовой корабль', 'Аккламатор-II', 'Rothana Heavy Engineering', null, 730, 'http://starforge.info/old-republic-ships/acclamator-class-cruisers/');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(12, 752, 460, 183);
insert into ship_crew(ship_id, minimal, normal) values(12, null, 2014);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(12, 40, 1200, 0.75, null);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(12, 120, 960, 20, 248);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(12, 15, 24);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(12, 22, 2);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(12, 3, 16);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(12, 1, 3200);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(12, 2, 10);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(12, 3, 8);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(12, 4, 64);

insert into ship(id, type, ship_class, producer, cost, autonomy, link) values(13, 'Звездный Разрушитель', 'Победа-II', 'Rendili StarDrive ', 50000000, 1460, 'http://starforge.info/galactic-empire-ships/victory-ii-class-star-destroyer/');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(13, 900, 564, 289);
insert into ship_crew(ship_id, minimal, normal) values(13, 2100, 6107);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(13, 60, null, 1, 15);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(13, 150, 1380, 20, 216);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(13, 15, 20);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(13, 1, 20);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(13, 11, 10);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(13, 4, 10);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(13, 1, 4);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(13, 2, 24);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(13, 4, 6);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(12, 1, 1600);

insert into ship(id, type, ship_class, producer, cost, autonomy, link) values(14, 'Звездный Разрушитель', 'Император-I', 'Kuat Drive Yards  ', 130000000, 2190, 'http://starforge.info/galactic-empire-ships/imperator-i-class-star-destroyer/');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(14, 1600, null, null);
insert into ship_crew(ship_id, minimal, normal) values(14, 5600 , 37085);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(14, 60, null, 2, 8);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(14, 150, 2100, 20, 256);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(14, 12, 60);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(14, 23, 60);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(14, 4, 10);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(14, 1, 8);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(14, 2, 72);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(14, 4, 12);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(14, 5, 15);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(14, 6, 12);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(14, 7, 4);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(14, 5, 9700);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(14, 6, 20);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(14, 7, 30);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(14, 8, 1);

insert into ship(id, type, ship_class, producer, cost, autonomy, link) values(15, 'Звездный Разрушитель', 'Император-II', 'Kuat Drive Yards  ', 147000000, 2190, 'http://starforge.info/galactic-empire-ships/imperator-ii-class-star-destroyer/');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(15, 1600, null, null);
insert into ship_crew(ship_id, minimal, normal) values(15, 5000 , 37085);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(15, 60, null, 2, 8);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(15, 150, 2100, 20, 256);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(15, 24, 50);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(15, 12, 50);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(15, 11, 20);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(15, 8, 10);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(15, 2, 72);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(15, 5, 9700);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(15, 6, 20);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(15, 7, 30);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(15, 8, 1);

insert into ship(id, type, ship_class, producer, cost, autonomy, link) values(16, 'Линейный корабль', 'Палач', 'Kuat Drive Yards  ', 1143350000, 2190, 'http://starforge.info/galactic-empire-ships/executor-class-star-destroyer/');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(16, 19000, null, null);
insert into ship_crew(ship_id, minimal, normal) values(16, null , 280734);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(16, 40, null, 2, 10);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(16, 400, 3000, 20, 574);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(16, 25, 250);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(16, 12, 250);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(16, 23, 250);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(16, 3, 250);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(16, 8, 40);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(16, 26, 500);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(16, 2, 144);
insert into ship_hanger(ship_id, small_aircraft_id, small_aircraft_count) values(16, 4, 200);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(16, 1, 38000);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(16, 8, 3);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(16, 6, 30);
insert into ship_landing_deck(ship_id, land_force_id, landing_deck_count) values(16, 7, 50);

insert into ship(id, type, ship_class, producer, cost, autonomy, link) values(17, 'Осадный корабль', 'Торпедная Сфера', 'Loronar Corporation  ', 327830000, 2190, 'http://starforge.info/space-station/torpedo-sphere/');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(17, 1900, 1900, 1900);
insert into ship_crew(ship_id, minimal, normal) values(17, 20415 , 63275);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(17, 20, null, 3, 18);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(17, 100, 3000, 20, 512);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(17, 12, 10);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(17, 3, 500);

insert into ship(id, type, ship_class, producer, cost, autonomy, link) values(18, 'Корвет', 'Крестоносец', 'Mandal Hypernautics', 5200000, 365, 'http://starforge.info/other-ships/crusader-class-corvette/');
insert into ship_size(ship_id, ship_length, ship_width, ship_height) values(18, 150, null, null);
insert into ship_crew(ship_id, minimal, normal) values(18, null , 80);
insert into ship_speed(ship_id, space_speed, atmosphere_speed, main_hyper_drive_class, backup_hyper_drive_class) values(18, 80, null, 1, 12);
insert into ship_defence(ship_id, shields, hp, dr, damage_threshold) values(18, 120, 950, 15, 136);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(18, 17, 10);
insert into ship_weapon(ship_id, weapon_id, weapon_count) values(18, 27, 2);
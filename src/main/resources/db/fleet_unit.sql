insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(1, 'Ударная линия v1', 1, '<b>3-6</b> судов.<br/>Если крейсеры или тяж крейсеры - не более <b>3</b><br/><h5>Задачи:</h5><ul><li>открытое противостояние вражеским корабельным соединениям аналогичного уровня;</li><li>нападение на ранее обнаруженные базы;</li><li>обеспечением безопасности судоходства</li></ul>');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(1, 9, 1);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(1, 7, 2);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(1, 13, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(12, 'Ударная линия v2', 1, '<b>3-6</b> судов.<br/>Если сли легкие крейсеры и фрегаты - <b>6</b><br/><h5>Задачи:</h5><ul><li>открытое противостояние вражеским корабельным соединениям аналогичного уровня;</li><li>нападение на ранее обнаруженные базы;</li><li>обеспечением безопасности судоходства</li></ul>');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(12, 3, 3);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(12, 5, 3);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(12, 13, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(2, 'Разведывательная линия', 1, '<b>4-8</b> кораблей.<br/>Легкие крейсеры, фрегаты, сец. модификации.<h5>Задачи:</h5><ul><li>обнаружение кораблей или баз противника и наведении на них УЛ или ТУЛ;</li><li>Если же противник начинал атаку РЛ, отходить, не вступая в бой</li></ul>');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(2, 3, 2);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(2, 18, 6);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(3, 'Разведывательно-ударная линия', 1, '<b>4-20</b> корветов.<h5>Задачи:</h5><ul><li>преследование, перехват и уничтожение легких вражеских кораблей и судов, а также групп МЛА (истребителей и бомбардировщиков) в различных тактических ситуациях</li></ul>');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(3, 1, 20);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(4, 'Линия преследования', 1, '<b>4-10</b> кораблей.<br/>Как правило легкие крейсеры.<h5>Задачи:</h5><ul><li>преследования отступающего противника;</li><li>не удалось перехватить цель, просчитать вектор гиперпрыжка и последовать за отступающим противником;</li><li>перехват вражеских эскадр на этапах промежуточных гиперпространственных переходов и связывание их боем до подхода УЛ или ТУЛ</li></ul>');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(4, 5, 5);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(4, 1, 5);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(4, 13, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(5, 'Тяжелая ударная линия v1', 1, '<b>Победа 2</b> или <b>Империал 2</b> мог считаться ТУЛом для ударного флота');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(5, 15, 1);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(5, 2, 2);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(13, 'Тяжелая ударная линия v2', 1, '<b>4-8</b> кораблей не ниже легкого крейсера');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(13, 9, 1);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(13, 10, 1);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(13, 7, 2);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(13, 5, 4);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(13, 13, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(20, 'Тяжелая ударная линия v3', 1, '<b>Победа 1</b> или <b>Империал</b> мог считаться ТУЛом для штурмового флота');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(20, 14, 1);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(20, 2, 2);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(14, 'Торпедная линия', 1, '<h5>Задачи:</h5><ul><li>уничтожения планетарных генераторов защитного поля;</li><li>проведения орбитальной бомбардировки</li></ul>');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(14, 17, 2);

insert into fleet_unit(id, name, minimum_command_rank_id) values(15, 'Десантная линия', 1);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(15, 19, 3);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(15, 20, 12);

insert into fleet_unit(id, name, minimum_command_rank_id) values(28, 'Линия противовоздушной обороны', 1);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(28, 21, 3);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(28, 6, 6);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(28, 3, 6);

insert into fleet_unit(id, name, minimum_command_rank_id) values(29, 'Линия противокосмической обороны', 1);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(29, 8, 3);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(29, 9, 3);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(31, 'Лёгкая патрульная линия', 1, '<h5>Задачи:</h5>патрулирование в пределах звездной системы в районах вне торговых/транспортных маршрутов, районов добычи');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(31, 3, 3);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(31, 1, 5);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(32, 'Тяжёлая патрульная линия', 1, '<h5>Задачи:</h5>патрулирование торговых/транспортных маршрутов, районов добычи, районов потенциального укрытия пиратов/контрабандистов/прочих противников');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(32, 10, 3);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(32, 5, 3);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(33, 'Линия планетарной обороны', 1, '<h5>Задачи:</h5>поддержка и контроль инспеции входящего и исходящего трафика, защита планеты');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(33, 1, 8);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(33, 3, 3);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(33, 9, 3);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(33, 13, 2);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(34, 'Линия быстрого реагирования', 1, '<h5>Задачи:</h5>вылет на тревожный сигнал от патрульной линии');
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(34, 13, 2);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(34, 9, 4);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(34, 15, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(35, 'Эскадра системной обороны 3', 2, '<h5>Задачи:</h5>обеспечение безопасности звездной системы с уровнем важности 3 (обычная колония), действие только в заданной системе');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(35, 33, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(35, 34, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(35, 32, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(35, 31, 3);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(35, 22, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(36, 'Эскадра системной обороны 2', 2, '<h5>Задачи:</h5>обеспечение безопасности звездной системы с уровнем важности 2 (важная колония, содержащая верфи, важные производства и пр.), действие только в заданной системе');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(36, 33, 2);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(36, 34, 2);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(36, 32, 2);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(36, 31, 6);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(36, 22, 2);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(37, 'Эскадра системной обороны 1', 2, '<h5>Задачи:</h5>Задача: обеспечение безопасности звездной системы с уровнем важности 1 (столица государства), действие только в заданной системе');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(37, 33, 4);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(37, 34, 4);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(37, 32, 5);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(37, 31, 10);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(37, 22, 4);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(38, 'Эскадра системной обороны 0', 2, '<h5>Задачи:</h5>обеспечение безопасности звездной системы с уровнем важности 0 (столица сверхгосударства), действие только в заданной системе');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(38, 33, 6);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(38, 34, 5);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(38, 32, 7);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(38, 31, 15);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(38, 22, 6);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(39, 'Эскадра секторальной обороны', 2, '<h5>Задачи:</h5>контроль над определенным сектором, патрулирование необжитых систем, реагирование на тревожный сигнал в обжитой системе, входящей в сектор');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(39, 32, 2);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(39, 31, 3);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(39, 34, 2);
insert into fleet_unit_composition_by_ships(fleet_unit_id, ship_id, ship_count) values(39, 22, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(6, 'Тяжелая эскадра v1', 2, '<h5>Задачи:</h5><ul><li>доразведку и основной удар по кораблям противника или охраняемому ими объекту;</li><li>охраны стратегически важного объекта в открытом космосе;</li><li>обеспечение блокады или охраны орбитального пространства вокруг планеты</li></ul>');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(6, 5, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(6, 13, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(6, 1, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(6, 2, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(7, 'Тяжелая эскадра v2', 2, '<h5>Задачи:</h5><ul><li>доразведку и основной удар по кораблям противника или охраняемому ими объекту;</li><li>охраны стратегически важного объекта в открытом космосе;</li><li>обеспечение блокады или охраны орбитального пространства вокруг планеты</li></ul>');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(7, 5, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(7, 13, 2);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(7, 3, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(21, 'Тяжелая эскадра v3', 2, '<h5>Задачи:</h5><ul><li>доразведку и основной удар по кораблям противника или охраняемому ими объекту;</li><li>охраны стратегически важного объекта в открытом космосе;</li><li>обеспечение блокады или охраны орбитального пространства вокруг планеты</li></ul>');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(21, 20, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(8, 'Легкая эскадра v1', 2, '<h5>Задачи:</h5><ul><li>действовать в регионах где, как считается, нет крупных сил противника обладающих тяжелыми крейсерами или линкорами;</li><li>патрулированием проблемных регионов; разведывательные операции;</li><li>сопровождения конвоев;</li><li>рейдовые операции;</li><li>если ЛЭ встречает сильное сопротивление - отступить и призвать на помощь крупные соединения имеющие корабли более тяжелого класса</li></ul>');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(8, 1, 2);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(8, 12, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(8, 2, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(8, 3, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(9, 'Легкая эскадра v2', 2, '<h5>Задачи:</h5><ul><li>действовать в регионах где, как считается, нет крупных сил противника обладающих тяжелыми крейсерами или линкорами;</li><li>патрулированием проблемных регионов; разведывательные операции;</li><li>сопровождения конвоев;</li><li>рейдовые операции;</li><li>если ЛЭ встречает сильное сопротивление - отступить и призвать на помощь крупные соединения имеющие корабли более тяжелого класса</li></ul>');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(9, 4, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(9, 2, 2);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(9, 3, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(16, 'Линейная эскадра', 2, 'Основной тип соединений имперского флота для боя с эскадрами противника обладающих тяжелыми крейсерами или линкорами');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(16, 5, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(16, 1, 2);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(16, 4, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(17, 'Тяжелая десантная эскадра', 2, '<h5>Задача:</h5>Высадка на враждебную территорию, подготовка плацдарма для прибытия подкреплений');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(17, 15, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(17, 28, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(17, 29, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(17, 20, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(30, 'Лёгкая десантная эскадра', 2, '<h5>Задача:</h5>Высадка на подготовленный плацдарм и усиление наступления');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(30, 15, 2);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(30, 28, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(30, 20, 1);


insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(18, 'Бомбардировочная эскадра', 2, '<h5>Задача:</h5>Для произведения разрушительных орбитальных бомбардировок и оказания огневой поддержки наземным подразделениям');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(18, 14, 2);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(18, 3, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(18, 4, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(10, 'Эскортная флотская группировка', 3, '<h5>Задача:</h5>Защита судоходства в важных системах, обеспечение безопасности станций и орбитального пространства крупных планет, таможенный досмотр судов прибывающих и покидающих систему и борьба с пиратами.');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(10, 6, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(10, 7, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(10, 8, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(10, 9, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(19, 'Ударная флотская группировка', 3, '<h5>Задача:</h5>Обеспечение полного отсутствия враждебных кораблей в орбитальном пространстве контролируемых миров и прекращение их деятельности во всей системе');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(19, 16, 3);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(19, 8, 2);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(22, 'Десантная флотская группировка', 3, '<h5>Задача:</h5>Осуществление стратегической десантной операции против хорошо вооруженного и многочисленного противника окопавшегося на поверхности планеты');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(22, 17, 5);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(22, 30, 5);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(22, 8, 5);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(23, 'Бомбардировочная флотская группировка', 3, 'База-Дельта-Ноль');
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(23, 18, 3);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(23, 8, 1);

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(24, 'Корабельное соединение технической поддержки', 3, 'КСТП можно по праву назвать подвижной ремонтной мастерской и одновременно эвакуационной службой.<br/>В состав соединения входят восемь судов, часто являющихся Evakmar-KDY или переоборудованными тяжелыми транспортниками.<br/>На борту этих судов находится множество технических специалистов, небольших ремонтных ботов и запас запчастей, двигателей, гипердвигателей и отдельных элементов корабельных силовых установок');

insert into fleet_unit(id, name, minimum_command_rank_id, comments) values(25, 'Военно-транспортное корабельное соединение', 3, 'Типичное ВТКС полного состава включало более 100 судов всех размеров и классов.<br/>Помимо транспортников в состав соединения входили госпитальные (медицинские) корабли.<br/>Экипажи комплектовались квалифицированным техническим и медицинским персоналом, имели специализированные дроиды (в том числе дроиды-грузчики) и небольшие боты.<br/>Возможности ВТКС позволяли осуществлять доставку на корабли припасов прямо в космосе.');

insert into fleet_unit(id, name, minimum_command_rank_id) values(11, 'Штурмовой флот', 4);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(11, 23, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(11, 22, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(11, 24, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(11, 25, 1);

insert into fleet_unit(id, name, minimum_command_rank_id) values(26, 'Ударный флот', 4);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(26, 19, 3);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(26, 10, 2);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(26, 24, 1);
insert into fleet_unit_composition_by_fleet_units(parent_fleet_unit_id, child_fleet_unit_id, fleet_unit_count) values(26, 25, 1);
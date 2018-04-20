insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role,city,phone) values (1,'16-10-15','maja.gulan@gmail.com','sifra','Maja','Gulan','GUEST','Novi Sad','063212121');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role,city,phone) values (2,'16-10-15','smiljana.tedic@gmail.com','sifra','Smiljana','Tedic','GUEST','Beograd','123123123');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role,city,phone) values (13,'16-10-15','milana.carapic@gmail.com','sifra','Milana','Carapic','GUEST','Visegrad','123123123');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role,city,phone) values (14,'16-10-15','guest14@gmail.com','sifra','Test','Test','GUEST','Novi Sad','123123123');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role,city,phone) values (15,'16-10-15','guest15@gmail.com','sifra','Test','Test','GUEST','Sombor','123123123');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role,city,phone) values (16,'16-10-15','guest16@gmail.com','sifra','Test','Test','GUEST','Pec','123123123');


insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role,city,phone) values (7,'16-10-15','admin@gmail.com','sifra','Milena','Markovic','SYSTEMMANAGER','Nis','123123123');

insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role,city,phone) values (9,'16-10-15','filip.visnjic@gmail.com','sifra','Filip','Visnjic','INSTITUTIONMANAGER','Kragujevac','123123123');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role,city,phone) values (10,'16-10-15','miroslav.ilic@gmail.com','sifra','Miroslav','Ilic','INSTITUTIONMANAGER','Bor','123123123');

insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role,city,phone) values (11,'16-10-15','fun.manager@gmail.com','sifra','Batko','Batkic','FUNMANAGER','Trebinje','123123123');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role,city,phone) values (12,'16-10-15','funny.manager@gmail.com','sifra','Pera','Peric','FUNMANAGER','Modrica','123123123');



insert into bidder(user_id) values(11);
insert into bidder(user_id) values(12);

insert into institution(res_id, res_desc, res_name, ins_type, adr_ins, gmapsUrl) values(1,'Opis bioskopa','Arena cineplex', 'CINEMA','Bulevar Mihajla Pupina 3','https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2808.6982904757137!2d19.840478015955338!3d45.253893479099!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x475b1069ec9deca9%3A0xd7dc4b85b5fc753!2z0JHRg9C70LXQstCw0YAg0JzQuNGF0LDRmNC70LAg0J_Rg9C_0LjQvdCwIDMsINCd0L7QstC4INCh0LDQtA!5e0!3m2!1ssr!2srs!4v1524158968044');
insert into institution(res_id, res_desc, res_name, ins_type, adr_ins, gmapsUrl) values(2,'Opis pozorista','Narodno pozoriste Novi Sad', 'THEATER', 'Lasla Gala 23','https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2809.1092174864743!2d19.83506441555019!3d45.245584179099005!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x475b1015b93d3229%3A0xe7b7f1b2bf36ec57!2z0JvQsNGB0LvQsCDQk9Cw0LvQsCAyMywg0J3QvtCy0Lgg0KHQsNC0IDIxMDAw!5e0!3m2!1ssr!2srs!4v1523445804304');


insert into user_rank(user_rank_id,user_rank_type,user_rank_scale) values(1,'GOLD',200);
insert into user_rank(user_rank_id,user_rank_type,user_rank_scale) values(2,'SILVER',100);
insert into user_rank(user_rank_id,user_rank_type,user_rank_scale) values(3,'BRONZE',50);

insert into guest (user_id,status,guest_points,user_rank_user_rank_id) values (1,'ACTIVE',220,1);
insert into guest (user_id,status,guest_points,user_rank_user_rank_id) values (2,'ACTIVE',150,2);
insert into guest (user_id,status,guest_points,user_rank_user_rank_id) values (13,'ACTIVE',30,3);
insert into guest (user_id,status,guest_points,user_rank_user_rank_id) values (14,'ACTIVE',20,3);
insert into guest (user_id,status,guest_points,user_rank_user_rank_id) values (15,'ACTIVE',11,3);
insert into guest (user_id,status,guest_points,user_rank_user_rank_id) values (16,'ACTIVE',23,3);
insert into system_manager (user_id, predefined) values (7, true);


insert into friend(sender_id,reciever_id,status) values(1,2,false);
insert into friend(sender_id,reciever_id,status) values(1,14,false);
insert into friend(sender_id,reciever_id,status) values(1,15,false);
insert into friend(sender_id,reciever_id,status) values(1,16,false);
insert into friend(sender_id,reciever_id,status) values(1,13,true);
insert into friend(sender_id,reciever_id,status) values(13,2,true);
insert into friend(sender_id,reciever_id,status) values(13,14,true);
insert into friend(sender_id,reciever_id,status) values(13,15,false);
insert into friend(sender_id,reciever_id,status) values(13,16,false);
insert into friend(sender_id,reciever_id,status) values(13,1,false);

insert into institution_manager (user_id,institution_res_id) values (9,1);
insert into institution_manager (user_id,institution_res_id) values (10,1);

insert into projection(pr_id, pr_des, pr_name, pr_actor, pr_duration, pr_price, projection_type,pr_director) values(1,'description1','Star Wars 1','Harison Ford', '2h',120.3,'MOVIE','Stiven Spilberg');
insert into projection(pr_id, pr_des, pr_name, pr_actor, pr_duration, pr_price, projection_type,pr_director) values(2,'description2','Hobit','Martin Freeman', '2h',750.0,'MOVIE','J.J. Abrams');
insert into projection(pr_id, pr_des, pr_name, pr_actor, pr_duration, pr_price, projection_type,pr_director) values(3,'description3','Labudovo jezero','Ana Mihajlovski', '2h',150.0,'PLAY','Tarantino');
insert into projection(pr_id, pr_des, pr_name, pr_actor, pr_duration, pr_price, projection_type,pr_director) values(4,'description4','Pop Cira i pop Spira','Dragan Bjelogrlic', '2h',350.0,'PLAY','Jack Sparrow');

insert into institution_projections(pr_id, res_id) values (1, 1);
insert into institution_projections(pr_id, res_id) values (2, 1);
insert into institution_projections(pr_id, res_id) values (3, 2);
insert into institution_projections(pr_id, res_id) values (4, 2);

insert into request_offer(id, name,description,expiration_date, start_date, status, fun_manager_user_id,guest_user_id)  values (1,'Requisite1','Description1', '12-12-17', '10-12-15', true, 11,null);
insert into request_offer(id,name,description, expiration_date, start_date, status, fun_manager_user_id,guest_user_id)  values (2,'Requisite2','Description2', '12-12-15', '10-12-15', true, 11,null);
insert into request_offer(id,name,description, expiration_date, start_date, status, fun_manager_user_id,guest_user_id)  values (3,'Requisite3','Description3', '12-12-15', '10-12-15', true, null,1);
insert into request_offer(id,name,description, expiration_date, start_date, status, fun_manager_user_id,guest_user_id)  values (4,'Requisite4','Description4', '12-12-15', '10-12-15', true, null,1);

insert into bidder_offer(id, bo_dod, bo_garanty, bo_price, bidder_user_id, request_offer_id) values (1, '13-12-15', 'Return money', 300.3, 1, 1);
insert into bidder_offer(id, bo_dod, bo_garanty, bo_price, bidder_user_id, request_offer_id) values (2, '13-12-15', 'Return money', 300.3, 1, 2);

insert into segment(id, sgm_pos, institution_res_id, height, width) values(1,'VIP',1,4,3);
insert into segment(id, sgm_pos, institution_res_id, height, width) values(2,'BOX',1,5,3);
insert into segment(id, sgm_pos, institution_res_id, height, width) values(3,'PARTERRE',1,4,6);

insert into segment(id, sgm_pos, institution_res_id, height, width) values(4,'SEGMENT1',2,4,3);
insert into segment(id, sgm_pos, institution_res_id, height, width) values(5,'SEGMENT2',2,5,3);
insert into segment(id, sgm_pos, institution_res_id, height, width) values(6,'SEGMENT3',2,4,6);

insert into institution_table(id, res_free,segment_id, table_row, table_column) values(1,true,1,0,0);
insert into institution_table(id, res_free, segment_id, table_row, table_column) values(2,true,1,0,2);
insert into institution_table(id, res_free ,segment_id, table_row, table_column) values(3,true,1,1,1);
insert into institution_table(id, res_free, segment_id, table_row, table_column) values(10,true,1,2,1);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(9,true,1,2,0);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(11,true,1,2,2);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(4,true,2,0,0);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(5,true,2,0,1);
insert into institution_table(id, res_free, segment_id, table_row, table_column) values(6,true,2,1,2);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(7,true,3,1,3);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(8,true,3,1,4);
insert into institution_table(id, res_free,segment_id, table_row, table_column) values(12,true,4,0,0);
insert into institution_table(id, res_free, segment_id, table_row, table_column) values(13,true,4,0,2);
insert into institution_table(id, res_free ,segment_id, table_row, table_column) values(14,true,4,1,1);
insert into institution_table(id, res_free, segment_id, table_row, table_column) values(15,true,4,2,1);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(16,true,4,2,0);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(17,true,4,2,2);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(18,true,5,0,0);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(19,true,5,0,1);
insert into institution_table(id, res_free, segment_id, table_row, table_column) values(20,true,5,1,2);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(21,true,6,1,3);
insert into institution_table(id, res_free,  segment_id, table_row, table_column) values(22,true,6,1,4);

insert into projection_time(id,start_time,projection_pr_id) values (1,10.0,1);
insert into projection_time(id,start_time,projection_pr_id) values (2,12.0,1);
insert into projection_time(id,start_time,projection_pr_id) values (3,14.0,1);
insert into projection_time(id,start_time,projection_pr_id) values (4,16.0,1);
insert into projection_time(id,start_time,projection_pr_id) values (5,18.0,1);
insert into projection_time(id,start_time,projection_pr_id) values (6,20.0,1);
insert into projection_time(id,start_time,projection_pr_id) values (7,11.0,2);
insert into projection_time(id,start_time,projection_pr_id) values (8,13.0,2);
insert into projection_time(id,start_time,projection_pr_id) values (9,15.0,2);
insert into projection_time(id,start_time,projection_pr_id) values (10,17.0,2);

insert into reservation(id,reservation_start,reservation_end,institution_res_id) values (1,12.0,13.30,1);
insert into reservation(id, RESERVATION_START, RESERVATION_END,institution_res_id) values (2, 20.20, 21.20,1);
insert into reservation(id, RESERVATION_START, RESERVATION_END,institution_res_id) values (3, 18.20, 20.20,1);
insert into reservation(id, RESERVATION_START, RESERVATION_END,institution_res_id) values (4, 17.20, 18.20,1);



insert into guest_reservations(user_id,res_id) values (1,1);
insert into guest_reservations(user_id,res_id) values (2,1);

insert into res_ord(res_ord_id, res_ord_date, order_time, table_id,price,order_status,projection_pr_id,fast_reservation,discount) values(1,'18-04-12',10.0,1,0,'NOTPAID',1,true,10);
insert into res_ord(res_ord_id, res_ord_date, order_time, table_id,price,order_status,projection_pr_id,fast_reservation,discount) values(2,'18-04-15',17.47,3,0,'NOTPAID',2,true,20);
insert into res_ord(res_ord_id, res_ord_date, order_time, table_id, price,order_status, reservation_id,projection_pr_id,fast_reservation,discount) values(3,'18-04-21',12.0,11,990.6,'PAID',1,3,true,30);
insert into res_ord(res_ord_id, res_ord_date, order_time, table_id, price,order_status, reservation_id,projection_pr_id,fast_reservation,discount) values(4,'18-04-21',17.47,4,0,'PAID',1,3,true,40);
insert into res_ord(res_ord_id, res_ord_date, order_time, table_id, price,order_status, reservation_id,projection_pr_id,fast_reservation,discount) values(5,'18-04-23',17.47,5,0,'NOTPAID',2,3,true,50);
insert into res_ord(res_ord_id, res_ord_date, order_time, table_id,price,order_status, reservation_id,projection_pr_id,fast_reservation,discount) values(6,'18-04-23',17.47,6,0,'NOTPAID',3,3,true,60);


insert into grade(grd_id, grd_meal, grd_res,reservation_id, guest_user_id, projection_pr_id, institution_res_id) values (1, 3, 6,1, 1, 3, 1);
insert into grade(grd_id, grd_meal, grd_res,reservation_id, guest_user_id, projection_pr_id, institution_res_id) values (2, 4, 5,1, 2, 3, 1);
insert into grade(grd_id, grd_meal, grd_res,reservation_id, guest_user_id, projection_pr_id, institution_res_id) values (3, 9, 6,2, 13, 4, 1);
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (1,'16-10-15','maja.gulan@gmail.com','sifra','Maja','Gulan','GUEST');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (2,'16-10-15','smiljana.tedic@gmail.com','sifra','Smiljana','Tedic','GUEST');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (13,'16-10-15','guest13@gmail.com','sifra','Test','Test','GUEST');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (14,'16-10-15','guest14@gmail.com','sifra','Test','Test','GUEST');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (15,'16-10-15','guest15@gmail.com','sifra','Test','Test','GUEST');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (16,'16-10-15','guest16@gmail.com','sifra','Test','Test','GUEST');


insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (7,'16-10-15','admin@gmail.com','sifra','Milena','Markovic','SYSTEMMANAGER');

insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (9,'16-10-15','filip.visnjic@gmail.com','sifra','Filip','Visnjic','INSTITUTIONMANAGER');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (10,'16-10-15','miroslav.ilic@gmail.com','sifra','Miroslav','Ilic','INSTITUTIONMANAGER');

insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (11,'16-10-15','fun.manager@gmail.com','sifra','Batko','Batkic','FUNMANAGER');
insert into user (user_id,user_date,user_email,user_pass,user_name,user_surname,user_role) values (12,'16-10-15','funny.manager@gmail.com','sifra','Pera','Peric','FUNMANAGER');



insert into bidder(user_id) values(11);
insert into bidder(user_id) values(12);

insert into institution(res_id, res_desc, res_name, ins_type, adr_ins) values(1,'Opis bioskopa','Arena cineplex', 'CINEMA','Bulevar Despota Stefana 25');
insert into institution(res_id, res_desc, res_name, ins_type, adr_ins) values(2,'Opis pozorista','Narodno pozoriste Novi Sad', 'THEATER', 'Kisacka 25');


insert into guest (user_id,status) values (1,'ACTIVE');
insert into guest (user_id,status) values (2,'ACTIVE');
insert into guest (user_id,status) values (13,'ACTIVE');
insert into guest (user_id,status) values (14,'ACTIVE');
insert into guest (user_id,status) values (15,'ACTIVE');
insert into guest (user_id,status) values (16,'ACTIVE');
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

insert into projection(pr_id, pr_des, pr_name, pr_actor, pr_duration, pr_price, projection_type) values(1,'description1','Star Wars 1','Harison Ford', '2h',120.3,'MOVIE');
insert into projection(pr_id, pr_des, pr_name, pr_actor, pr_duration, pr_price, projection_type) values(2,'description2','Hobit','Martin Freeman', '2h',750.0,'MOVIE');
insert into projection(pr_id, pr_des, pr_name, pr_actor, pr_duration, pr_price, projection_type) values(3,'description3','Labudovo jezero','Ana Mihajlovski', '2h',150.0,'PLAY');
insert into projection(pr_id, pr_des, pr_name, pr_actor, pr_duration, pr_price, projection_type) values(4,'description4','Pop Cira i pop Spira','Dragan Bjelogrlic', '2h',350.0,'PLAY');

insert into institution_projections(pr_id, res_id) values (1, 1);
insert into institution_projections(pr_id, res_id) values (2, 1);
insert into institution_projections(pr_id, res_id) values (3, 2);
insert into institution_projections(pr_id, res_id) values (4, 2);

insert into request_offer(id, expiration_date, start_date, status, fun_manager_user_id)  values (1, '12-12-17', '10-12-15', true, 11);
insert into request_offer(id, expiration_date, start_date, status, fun_manager_user_id)  values (2, '12-12-15', '10-12-15', true, 11);

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

insert into res_ord(res_ord_id, res_ord_date, order_time, table_id,price,order_status,projection_pr_id,fast_reservation) values(1,'17-03-02',10.0,1,0,'NOTPAID',1,true);
insert into res_ord(res_ord_id, res_ord_date, order_time, table_id,price,order_status,projection_pr_id,fast_reservation) values(2,'17-03-02',17.47,3,0,'NOTPAID',2,true);
insert into res_ord(res_ord_id, res_ord_date, order_time, table_id, price,order_status, reservation_id,projection_pr_id,fast_reservation) values(3,'17-02-28',12.0,11,990.6,'PAID',1,3,true);
insert into res_ord(res_ord_id, res_ord_date, order_time, table_id, price,order_status, reservation_id,projection_pr_id,fast_reservation) values(4,'17-02-27',17.47,4,0,'PAID',1,3,true);
insert into res_ord(res_ord_id, res_ord_date, order_time, table_id, price,order_status, reservation_id,projection_pr_id,fast_reservation) values(5,'17-02-18',17.47,5,0,'NOTPAID',2,3,true);
insert into res_ord(res_ord_id, res_ord_date, order_time, table_id,price,order_status, reservation_id,projection_pr_id,fast_reservation) values(6,'17-02-28',17.47,6,0,'NOTPAID',3,3,true);


insert into grade(grd_id, grd_meal, grd_res,reservation_id, guest_user_id, projection_pr_id, institution_res_id) values (1, 3, 6,1, 1, 3, 1);
insert into grade(grd_id, grd_meal, grd_res,reservation_id, guest_user_id, projection_pr_id, institution_res_id) values (2, 4, 5,1, 2, 3, 1);
insert into grade(grd_id, grd_meal, grd_res,reservation_id, guest_user_id, projection_pr_id, institution_res_id) values (3, 9, 6,2, 13, 4, 1);
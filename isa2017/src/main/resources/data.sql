--AdminSis
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (1,'adminsis1@gmail.com','adminsis1','AdminSis1','AdminicSis1','Sombor','0651231231231','16-10-17','ADMINSIS');
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (2,'adminsis2@gmail.com','adminsis2','AdminSis2','AdminicSis2','Nis','0651234531231','16-10-17','ADMINSIS');
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (3,'adminsis3@gmail.com','adminsis3','AdminSis3','AdminicSis3','Beograd','06345231231','16-10-17','ADMINSIS');

--AdminUs
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (4,'adminus1@gmail.com','adminus1','AdminUs1','AdminicUs1','Novi Sad','0632431231','16-10-17','ADMINUS');
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (5,'adminus2@gmail.com','adminus2','AdminUs2','AdminicUs2','Zrenjanin','0664531231231','16-10-17','ADMINUS');
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (6,'adminus3@gmail.com','adminus3','AdminUs3','Adminicus3','Vrsac','061231231','16-10-17','ADMINUS');

--AdminFan
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (7,'adminfan1@gmail.com','adminfan1','AdminFan1','AdminicFan1','Pirot','063251231','16-10-17','ADMINFAN');
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (8,'adminfan2@gmail.com','adminfan2','AdminFan2','AdminicFan2','Zrenjanin','064531231231','16-10-17','ADMINFAN');
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (9,'adminfan3@gmail.com','adminfan3','AdminFan3','AdminicFan3','Leskovac','06131231','16-10-17','ADMINFAN');

--Posetilac
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (10,'pos1@gmail.com','pos1','Pos1','Posic1','Sremska Mitrovica','063254231','16-10-17','POSETILAC');
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (11,'pos2@gmail.com','pos2','Pos2','Posic2','Kac','06453131231','16-10-17','POSETILAC');
insert into korisnik (user_id,email,lozinka,ime,prezime,grad,broj_telefona,datum_rodjenja,korisnik_rola) values (12,'pos3@gmail.com','pos3','Pos3','Posic3','Prokuplje','0613121','16-10-17','POSETILAC');

--Prijatelj
--insert into friend(sender_id,reciever_id,status) values(10,11,false);
--insert into friend(sender_id,reciever_id,status) values(10,12,false);


--Glumac
insert into glumac (id,ime,prezime) values (1,'Pera','Peric');
insert into glumac (id,ime,prezime) values (2,'Zika','Sarenica');

--Mesto
insert into mesto (id,kolona,red,broj_Mesta,free) values (1,17,4,2,true);
insert into mesto (id,kolona,red,broj_Mesta,free) values (2,3,6,8,true);
insert into mesto (id,kolona,red,broj_Mesta,free) values (3,57,2,1,true);

--Ocena
insert into ocena (id,ocena_Projekcije, ocena_Ustanove) values (1,5,4);
insert into ocena (id,ocena_Projekcije, ocena_Ustanove) values (2,3,5);
insert into ocena (id,ocena_Projekcije, ocena_Ustanove) values (3,2,3);

--Ponuda
insert into ponuda (id,datum_ponude,cena_ponude) values (1,'16-10-17',436);
insert into ponuda (id,datum_ponude,cena_ponude) values (2,'15-3-17',577);
insert into ponuda (id,datum_ponude,cena_ponude) values (3,'17-2-17',398);

--Projekcija
insert into projekcija (pr_id,projekcija_tip,price,ime_reditelja,ime_projekcije,description,popust,poster,prosecna_ocena,trajanje,zanr) values (1,'FILM',200,'Reditelj','Neki film','Neki opis',30,'Neki poster',3,120,'TRILER');
insert into projekcija (pr_id,projekcija_tip,price,ime_reditelja,ime_projekcije,description,popust,poster,prosecna_ocena,trajanje,zanr) values (2,'FILM',300,'Stiven Spilberg','Neki film2','Neki opis2',50,'Neki poster2',5,90,'HOROR');
insert into projekcija (pr_id,projekcija_tip,price,ime_reditelja,ime_projekcije,description,popust,poster,prosecna_ocena,trajanje,zanr) values (3,'PREDSTAVA',400,'Tarantino','Neki film3','Neki opis3',60,'Neki poster3',5,220,'SF');

--Ustanova
insert into ustanova (ust_id,adresa,ustanova_Ime,description,ustanova_tip) values (1,'Milovana Glisica 1','Srpsko narodno pozoriste','Opis1','POZORISTE');
insert into ustanova (ust_id,adresa,ustanova_Ime,description,ustanova_tip) values (2,'Marka Miljanova 3','Arena cineplex','Opis2','BIOSKOP');
insert into ustanova (ust_id,adresa,ustanova_Ime,description,ustanova_tip) values (3,'Kosovska 50','Pozoriste mladih','Opis3','POZORISTE');

--Rezervacija
insert into rezervacija (id,end_Time,start_Time,stanje_rez) values (1,11.30,12.30,'NIJEPLACENO');
insert into rezervacija (id,end_Time,start_Time,stanje_rez) values (2,3.50,5.40,'NIJEPLACENO');
insert into rezervacija (id,end_Time,start_Time,stanje_rez) values (3,4.40,6.10,'PLACENO');

--Sala
insert into sala (id,broj_sale,kapacitet,naziv) values (1,1,100,'Bronzana sala');
insert into sala (id,broj_sale,kapacitet,naziv) values (2,2,200,'Srebrna sala');
insert into sala (id,broj_sale,kapacitet,naziv) values (3,4,400,'Zlatna sala');

--Segment
insert into segment (id,width,height,segment_tip) values (1,3,5,'BALKON');
insert into segment (id,width,height,segment_tip) values (2,4,5,'VIP');
insert into segment (id,width,height,segment_tip) values (3,6,8,'SEDISTA');

--Tematski rekvizit
insert into tematski_rekvizit (id,naziv,opis,originalna_cena,rok_ponuda,slika) values (1,'Rekv1','Opis1',300,'15-3-17','Slika1');
insert into tematski_rekvizit (id,naziv,opis,originalna_cena,rok_ponuda,slika) values (2,'Rekv2','Opis2',300,'1-3-17','Slika2');
insert into tematski_rekvizit (id,naziv,opis,originalna_cena,rok_ponuda,slika) values (3,'Rekv3','Opis3',300,'5-4-17','Slika3');

--Termin projekcije
insert into termin_projekcije (id,datum_odrzavanja,termin_odrzavanja) values (1,'5-4-17','11:30:00');
insert into termin_projekcije (id,datum_odrzavanja,termin_odrzavanja) values (2,'4-3-17','12:50:00');
insert into termin_projekcije (id,datum_odrzavanja,termin_odrzavanja) values (3,'16-4-17','5:40:00');


--Veza
insert into veza (id,status_veze) values (1,1);
insert into veza (id,status_veze) values (2,0);
insert into veza (id,status_veze) values (3,1);
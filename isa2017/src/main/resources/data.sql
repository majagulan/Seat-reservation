--AdminSis
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (1,'adminsis1@gmail.com','adminsis1','AdminSis1','AdminicSis1','Sombor','0651231231231','ADMINSIS');
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (2,'adminsis2@gmail.com','adminsis2','AdminSis2','AdminicSis2','Nis','0651234531231','ADMINSIS');
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (3,'adminsis3@gmail.com','adminsis3','AdminSis3','AdminicSis3','Beograd','06345231231','ADMINSIS');

--AdminUs
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (4,'adminus1@gmail.com','adminus1','AdminUs1','AdminicUs1','Novi Sad','0632431231','ADMINUS');
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (5,'adminus2@gmail.com','adminus2','AdminUs2','AdminicUs2','Zrenjanin','0664531231231','ADMINUS');
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (6,'adminus3@gmail.com','adminus3','AdminUs3','Adminicus3','Vrsac','061231231','ADMINUS');

--AdminFan
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (7,'adminfan1@gmail.com','adminfan1','AdminFan1','AdminicFan1','Pirot','063251231','ADMINFAN');
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (8,'adminfan2@gmail.com','adminfan2','AdminFan2','AdminicFan2','Zrenjanin','064531231231','ADMINFAN');
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (9,'adminfan3@gmail.com','adminfan3','AdminFan3','AdminicFan3','Leskovac','06131231','ADMINFAN');

--Posetilac
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (10,'pos1@gmail.com','pos1','Pos1','Posic1','Sremska Mitrovica','063254231','POSETILAC');
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (11,'pos2@gmail.com','pos2','Pos2','Posic2','Kac','06453131231','POSETILAC');
insert into korisnik (id,email,lozinka,ime,prezime,datum_rodjenja,korisnik_rola) values (12,'pos3@gmail.com','pos3','Pos3','Posic3','Prokuplje','0613121','POSETILAC');

--Prijatelj
insert into friend(sender_id,reciever_id,status) values(1,2,false);
insert into friend(sender_id,reciever_id,status) values(1,14,false);
insert into friend(sender_id,reciever_id,status) values(1,15,false);
insert into friend(sender_id,reciever_id,status) values(1,16,false);
insert into friend(sender_id,reciever_id,status) values(1,13,true);

--Glumac
insert into glumac (id,ime,prezime) values (1,'Pera','Peric');
insert into glumac (id,ime,prezime) values (2,'Zika','Sarenica');

--Karta
insert into karta (id,datum,originalna_cena,popust,vreme) values (1,'16-10-17',35.8,35,'11:30:00');
insert into karta (id,datum,originalna_cena,popust,vreme) values (2,'16-11-17',42.6,40,'8:37:00');
insert into karta (id,datum,originalna_cena,popust,vreme) values (3,'17-12-17',55.5,60,'12:30:00');

--Mesto
insert into mesto (id,redKolone,redMesta,brojMesta) values (1,17,4,2);
insert into mesto (id,redKolone,redMesta,brojMesta) values (2,3,6,8);
insert into mesto (id,redKoloneim,redMesta,brojMesta) values (3,57,2,1);

--Ocena
insert into ocena (id,gradeOfOrderItem, gradeOfUstanove) values (1,5,4);
insert into ocena (id,gradeOfOrderItem, gradeOfUstanove) values (2,3,5);
insert into ocena (id,gradeOfOrderItem, gradeOfUstanove) values (3,2,3);

--Ponuda
insert into ponuda (id,datum_ponude,cena_ponude) values (1,'16-10-17',436);
insert into ponuda (id,datum_ponude,cena_ponude) values (2,'15-3-17',577);
insert into ponuda (id,datum_ponude,cena_ponude) values (3,'17-2-17',398);

--Projekcija
insert into projekcija (id,imeProjekcije,price,ime_reditelja,naziv,description,popust,poster,prosecna_ocena,trajanje,zanr) values (1,'Mika',200,'Neki film','Neki opis',30,'Neki poster',3,120,'TRILER');
insert into projekcija (id,imeProjekcije,price,ime_reditelja,naziv,description,popust,poster,prosecna_ocena,trajanje,zanr) values (2,'Jovan',300,'Neki film2','Neki opis2',50,'Neki poster2',5,90,'HOROR');
insert into projekcija (id,imeProjekcije,price,ime_reditelja,naziv,description,popust,poster,prosecna_ocena,trajanje,zanr) values (3,'Milica',400,'Neki film3','Neki opis3',60,'Neki poster3',5,220,'SF');

--Rezervacija
insert into rezervacija (id,endTime,startTime,stanje_rez) values (1,'11:30:00','12:30:00','NIJEPLACENO');
insert into rezervacija (id,endTime,startTime,stanje_rez) values (2,'3:50:00','5:40:00','NIJEPLACENO');
insert into rezervacija (id,endTime,startTime,stanje_rez) values (3,'4:40:00','6:10:00','PLACENO');

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

--Ustanova
insert into ustanova (id,adresa,ustanovaIme,description,ustanova_tip) values (1,'Milovana Glisica 1','Srpsko narodno pozoriste','Opis1','POZORISTE');
insert into ustanova (id,adresa,ustanovaIme,description,ustanova_tip) values (2,'Marka Miljanova 3','Arena cineplex','Opis2','BIOSKOP');
insert into ustanova (id,adresa,ustanovaIme,description,ustanova_tip) values (3,'Kosovska 50','Pozoriste mladih','Opis3','POZORISTE');

--Veza
insert into veza (id,status_veze) values (1,1);
insert into veza (id,status_veze) values (2,0);
insert into veza (id,status_veze) values (3,1);
-- Produit 

insert into produit(description, prix,nom, nombre_jour_livraison)
values('25kg de Banane plantain origine Cameroon',25, 'Banane Plantain 25kg', 5)
,('25kg de Banane verte',14, 'Banane vert', 7), ('25kg de Banane rouge',35, 'Banane vert', 3);


---- destinataire 
insert into destinataire(nom, adresse,code_postal, ville, pays) values('serge', 'jean jaures', '78200', 'Houilles', 'France'),
('jean', 'jeane jaures', '78800', 'Conflans', 'France'), ('Jaures', 'liberte', '75018', 'Paris', 'France');
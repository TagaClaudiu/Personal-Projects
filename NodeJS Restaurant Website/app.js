const express = require('express');
const expressLayouts = require('express-ejs-layouts');
const bodyParser = require('body-parser')
const cookieParser=require('cookie-parser');
var session = require('express-session');
const sqlite3 = require('sqlite3').verbose();

const app = express();

const port = 6789;

app.use(session({
	secret: 'adiosesiune',
	resave: false,
	saveUninitialized: true,
	cookie: {secure: false}
  }));

app.set('view engine', 'ejs');

app.use(expressLayouts);

app.use(express.static('public'))

app.use(bodyParser.json());

app.use(bodyParser.urlencoded({ extended: true }));

app.use(cookieParser());

//variabile
const fs = require('fs');
const { Console } = require('console');
var db;

//nitty gritty

app.get('/', (req, res) => {
        res.render('index', {fullname: req.session.fullNameUser, username: req.session.usernameUser, role: req.session.roleUser, creatDB: req.session.flagCreatDB});
} );

app.get('/despre', (req, res) => {
   res.render('despre', {fullname: req.session.fullNameUser, username: req.session.usernameUser, role: req.session.roleUser, creatDB: req.session.flagCreatDB});
});

app.get('/meniu', (req, res) => {
    res.render('meniu', {fullname: req.session.fullNameUser, username: req.session.usernameUser, role: req.session.roleUser, creatDB: req.session.flagCreatDB, preparateDB: req.session.tabelPreparate, bauturiDB: req.session.tabelBauturi});
 });

 app.get('/contact', (req, res) => {
    res.render('contact', {fullname: req.session.fullNameUser, username: req.session.usernameUser, role: req.session.roleUser, creatDB: req.session.flagCreatDB});
 });

app.get('/autentificare', (req, res) => {
    res.render('autentificare', {eroare: req.cookies.eroare});
});

app.get('/deconectare', (req, res) => {
    req.session.usernameUser = null;
	req.session.fullNameUser = null;
	req.session.roleUser = null;
    if (req.session.flagCreatCos)
        {
            db.run(`DELETE FROM cos`, [], function(err) {
                if (err) {
                  return console.log(err.message);
                }
                console.log(`Table cos has been cleared`);
              });
        }
    req.session.flagCreatCos = false;
	res.redirect("/");
});

app.post('/comanda', (req, res) => {
    res.render('comanda', {fullname: req.session.fullNameUser, username: req.session.usernameUser, role: req.session.roleUser});
});

app.post('/trimite-comanda', (req, res) => {
	if (req.session.flagCreatCos)
        {
            db.run(`DELETE FROM cos`, [], function(err) {
                if (err) {
                  return console.log(err.message);
                }
                console.log(`Table cos has been cleared`);
              });
        }
    req.session.flagCreatCos = false;
    res.render('trimite-comanda');
});



app.post('/verificare-autentificare', (req, res) => {

	fs.readFile('utilizatori.json', (err, data) => {
		if (err) throw err;
		users = [];
		users = JSON.parse(data).Utilizatori;
		//console.log(users[0].username);
		let insertedValues = req.body;
		//console.log(insertedValues);

		let i = 0;
        let flag = false;
        while (i<users.length)
        {
		    if (insertedValues.Username == users[i].username && insertedValues.Password == users[i].password)
			    {

				    req.session.fullNameUser = users[i].fullname;
				    req.session.usernameUser = users[i].username;
				    req.session.roleUser = users[i].role;
                    flag = true;
                    break;
			    }
            else
                i++;
        }
        //let cookie = "";
        if (flag)
        {
            //res.cookie("corect", "Utilizator si parola corecte!", {expires: new Date(Date.now() + 5000)}); //5 secunde
           // cookie = res.cookies.corect;
            console.log("Mesaj: " + "Utilizator si parola corecte!");
            res.redirect("/");
            
        }
        else
        {
            res.cookie("eroare", "Utilizator sau parola gresite!", {expires: new Date(Date.now() + 5000)}); //5 secunde
            //cookie = res.cookies.corect;
            console.log("Mesaj: " + "Utilizator sau parola gresite!");
            res.redirect("/autentificare");
        }
    
    });
});

app.get('/creare-db', (req, res) => {
    req.session.flagCreatDB = true;
    req.session.flagCreatCos = true;

	db = new sqlite3.Database('public/db/meniu.db', sqlite3.OPEN_READWRITE | sqlite3.OPEN_CREATE, (err) => {
		if (err) {
			console.error(err.message);
		}
		else
			console.log("Connected to the meniu database");
	});

	db.run('CREATE TABLE preparate(ID int, Nume varchar(30), Ingrediente varchar(150), Gramaj int, Pret double(6, 2))');

    db.run('CREATE TABLE bauturi(ID int, Nume varchar(30), Optiuni varchar(150), Gramaj int, Pret double(6,2))');

	db.run('CREATE TABLE cos(ID int, Nume varchar(30), Ingrediente varchar(150), Gramaj int, Pret double(6,2))');

    

//Inserare Preparate
    setTimeout(function(){
    db.run(`INSERT INTO preparate VALUES(?,?,?,?,?)`, ['1', 'Cartofi Prăjiți', 'Cartofi', '200', '3.50'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO preparate VALUES(?,?,?,?,?)`, ['2', 'Cartofi Wedges', 'Cartofi', '200', '4.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO preparate VALUES(?,?,?,?,?)`, ['3', 'Hot Dog', 'Cremvusti, Paine, Varza, Rosie, Ketchup, Maioneza', '200', '5.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO preparate VALUES(?,?,?,?,?)`, ['4', 'Sandvis Pastramă ', 'Pastrama, Paine, Rosie, Ceapa, Salata, Branza', '200', '5.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO preparate VALUES(?,?,?,?,?)`, ['5', 'Sandvis Crispy', 'Crispy Strips, Paine, Rosie, Ceapa, Salata, Branza', '200', '10.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO preparate VALUES(?,?,?,?,?)`, ['6', 'Crispy Box', 'Crispy Strips, Cartofi prajiti, Salata, Rosii, Ceapa', '200', '15.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO preparate VALUES(?,?,?,?,?)`, ['7', 'Aripioare', 'Aripioare pui, Cartofi prajiti, Salata, Rosii, Ceapa', '200', '20.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO preparate VALUES(?,?,?,?,?)`, ['8', 'Aripioare Picante', 'Aripioare pui picante, Cartofi prajiti, Salata, Rosii, Ceapa', '200', '20.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });


      req.session.tabelPreparate = [];
      let sql = 'SELECT * FROM preparate ORDER BY ID';
	  let i = 0;

      setTimeout(function(){
        db.all(sql, [], (err, rows) => {
			if (err) {
					throw err;
					}
			rows.forEach((row) => {
				req.session.tabelPreparate[i] = [row.ID, row.Nume, row.Ingrediente, row.Gramaj, row.Pret];
				//console.log(row.Nume);
				i++;
				});
			console.table(req.session.tabelPreparate);
        });
      }, 1000);
    }, 1000);
//Gata preparate

//Inserare bauturi
setTimeout(function(){
    db.run(`INSERT INTO bauturi VALUES(?,?,?,?,?)`, ['101', 'Bere Draft', 'Bruna, Blonda', '500', '4.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO bauturi VALUES(?,?,?,?,?)`, ['102', 'Limonada', 'Simpla, Cu menta, Cu portocale', '300', '6.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO bauturi VALUES(?,?,?,?,?)`, ['103', 'Ceai', 'De menta, De fructe de padure', '250', '5.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO bauturi VALUES(?,?,?,?,?)`, ['104', 'Espresso', 'Scurt, Lung', '30', '4.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO bauturi VALUES(?,?,?,?,?)`, ['105', 'Coffee', 'Ice, Cinnamon latte, Americano, Latte Macchiato', '230', '5.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO bauturi VALUES(?,?,?,?,?)`, ['106', 'Suc', 'Coca Cola, Sprite, Fanta', '330', '4.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      db.run(`INSERT INTO bauturi VALUES(?,?,?,?,?)`, ['107', 'Shot', 'Vodka, Tequila, Jager-Bomb, Margarita, Campari', '50', '5.00'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });

      req.session.tabelBauturi = [];
      let sql = 'SELECT * FROM bauturi ORDER BY ID';
	  let i = 0;

      setTimeout(function(){
        db.all(sql, [], (err, rows) => {
			if (err) {
					throw err;
					}
			rows.forEach((row) => {
				req.session.tabelBauturi[i] = [row.ID, row.Nume, row.Optiuni, row.Gramaj, row.Pret];
				//console.log(row.Nume);
				i++;
				});
			console.table(req.session.tabelBauturi);
        });
      }, 1000);

}, 1000);
//Gata bauturi

//wait for the two to finish
setTimeout(function(){
    res.redirect('/meniu');
}, 4000);
	
});

app.post('/adaugare-cos', (req, res) => {

	let idProdusHelp = req.body;
	//console.log(idProdus);
	let idProdus = idProdusHelp.id;
	//console.log(mesaj);
	console.log(idProdus);

	let sql = 'SELECT * FROM preparate where ID = ?';
	req.session.indexCos = 0;
	db.each(sql, [idProdus], (err, row) => {
		if (err) {
			throw err;
		}
		db.run(`INSERT INTO cos VALUES(?,?,?,?,?)`, [row.ID, row.Nume, row.Ingrediente, row.Gramaj, row.Pret], function(err) {
				if (err) {
				  return console.log(err.message);
				}
				// get the last insert id
				console.log(`A row has been inserted in Cos with rowid ${this.lastID}`);
			  });

	})

});

app.get('/cos', (req, res) => {
	req.session.Cos = [];
	let i = 0;
	let sql = 'SELECT * FROM cos ORDER BY ID';

	db.all(sql, [], (err, rows) => {
		if (err) {
				throw err;
				}
		rows.forEach((row) => {
			req.session.Cos[i] = [row.ID, row.Nume, row.Ingrediente, row.Gramaj, row.Pret];
			//console.log(row.Nume);
			i++;
			});
		});
	setTimeout(function(){
		console.table(req.session.Cos);
		res.render('cos', {fullname: req.session.fullNameUser, username: req.session.usernameUser, role: req.session.roleUser,  creatDB: req.session.flagCreatDB, cosDB: req.session.Cos});
	}, 1000);

});

app.listen(port, () => console.log(`Serverul rulează la adresa http://localhost:6789`));
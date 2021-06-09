const express = require('express');
const expressLayouts = require('express-ejs-layouts');
const bodyParser = require('body-parser')
const cookieParser=require('cookie-parser');
var session = require('express-session');
const sqlite3 = require('sqlite3').verbose();

const app = express();

const port = 6789;

//sesiunea
app.use(session({
	secret: 'ilikeyou',
	resave: false,
	saveUninitialized: true,
	cookie: {secure: false}
  }));

// directorul 'views' va conține fișierele .ejs (html + js executat la server)
app.set('view engine', 'ejs');
// suport pentru layout-uri - implicit fișierul care reprezintă template-ul site-ului este views/layout.ejs
app.use(expressLayouts);
// directorul 'public' va conține toate resursele accesibile direct de către client (e.g., fișiere css, javascript, imagini)
app.use(express.static('public'))
// corpul mesajului poate fi interpretat ca json; datele de la formular se găsesc în format json în req.body
app.use(bodyParser.json());
// utilizarea unui algoritm de deep parsing care suportă obiecte în obiecte
app.use(bodyParser.urlencoded({ extended: true }));
// tasty cookies
app.use(cookieParser());


//variabile
let listaIntrebari;
let utilizatori;
const fs = require('fs');
const { Console } = require('console');
var db;

//var myRows = [];
// la accesarea din browser adresei http://localhost:6789/ se va returna textul 'Hello World'
// proprietățile obiectului Request - req - https://expressjs.com/en/api.html#req
// proprietățile obiectului Response - res - https://expressjs.com/en/api.html#res
app.get('/', (req, res) => {
		req.session.nrIncercari = 0;
		res.render('index', {numeIntreg: req.session.numeIntregUser, numeUtilizator: req.session.numeUtilizatorUser, database: req.session.myRows, rolUtilizator: req.session.rolUser});

} );

app.get('/autentificare', (req, res) => {
	
	res.render('autentificare', {error: req.cookies.mesajEroare});
} );



// la accesarea din browser adresei http://localhost:6789/chestionar se va apela funcția specificată
app.get('/chestionar', (req, res) => {

	fs.readFile('intrebari.json', (err, data) => {
		if (err) throw err;
		listaIntrebari = JSON.parse(data).listaIntrebari;
		// în fișierul views/chestionar.ejs este accesibilă variabila 'intrebari' care conține vectorul de întrebări
		res.render('chestionar', {intrebari: listaIntrebari, numeIntreg: req.session.numeIntregUser, numeUtilizator: req.session.numeUtilizatorUser});
	})

	
	
});

app.post('/rezultat-chestionar', (req, res) => {
	//console.log(req.body);
	let toateRezultatele = req.body;
	//let intrebare1 = toateRezultatele.intrebare0;
	//console.log(toateRezultatele);
	//console.log(intrebare1);
	fs.readFile('intrebari.json', (err, data) => {
		if (err) throw err;
		listaIntrebari = JSON.parse(data).listaIntrebari;
		res.render('rezultat-chestionar', {rezultat: toateRezultatele, intrebari: listaIntrebari, numeIntreg: req.session.numeIntregUser, numeUtilizator: req.session.numeUtilizatorUser});
	})
	//res.send("/chestionar' + JSON.stringify(req.body));
	//res.render('rezultat-chestionar', {rezultat: JSON.stringify(req.body)});
});

app.post('/verificare-autentificare', (req, res) => {

	fs.readFile('utilizatori.json', (err, data) => {
		if (err) throw err;

		utilizatori = [];
		utilizatori = JSON.parse(data).Utilizatori;
		console.log(utilizatori[0].username);

		let verificare = req.body;
		console.log(verificare);

		let i = 0;
		//for (i = 0; i<2; i++)
		{
		if (verificare.Username == utilizatori[i].username && verificare.Password == utilizatori[i].password)
			{

				req.session.numeIntregUser = utilizatori[i].fullname + " " + utilizatori[i].title;
				req.session.numeUtilizatorUser = utilizatori[i].username;
				req.session.rolUser =utilizatori[i].role;
	
				res.redirect("/");
				console.log("User si parola corecte");
			}
	
		else
		{
			i++;
			if (verificare.Username == utilizatori[i].username && verificare.Password == utilizatori[i].password)
			{

				req.session.numeIntregUser = utilizatori[i].fullname + " " + utilizatori[i].title;
				req.session.numeUtilizatorUser = utilizatori[i].username;
				req.session.rolUser =utilizatori[i].role;
	
				res.redirect("/");
				console.log("User si parola corecte");
			}
			else
			{
				req.session.nrIncercari++;
				if (req.session.nrIncercari > 2)
					{
						res.cookie("mesajEroare", "Ati gresit de 3 ori!", {expires: new Date(Date.now() + 5000)}); //5 secunde
						console.log("Gresit de 3 ori");
						if (req.session.nrIncercari > 3)
						{
							setTimeout(function(){
						res.status(403).send(
							{
								message: 'Access Forbidden'
							});
						}, 10000);
						}
						else
							res.redirect("/autentificare");
					}
				else
				{
					res.cookie("mesajEroare", "Utilizator sau parola gresite!", {expires: new Date(Date.now() + 5000)}); //5 secunde
					console.log("User si parola incorecte");
					res.redirect("/autentificare");
				}

				

			}
			
		}
		}

	})


		
});

app.get('/deconectare', (req, res) => {
	req.session.numeIntregUser = null;
	req.session.numeUtilizatorUser = null;
	req.session.rolUser = null;
	res.redirect("/");
});

app.post('/creare-db', (req, res) => {
	db = new sqlite3.Database('public/db/cumparaturi.db', sqlite3.OPEN_READWRITE | sqlite3.OPEN_CREATE, (err) => {
		if (err) {
			console.error(err.message);
		}
		else
			console.log("Connected to the cumparaturi database");
	});

	db.run('CREATE TABLE produse(ID int, Nume varchar(30), Stoc int, Pret double(5, 2))');

	db.run('CREATE TABLE cos(ID int, Nume varchar(30), Cantitate int, Pret double(5,2))');
	res.redirect('/');
	
});

app.post('/inserare-db', (req, res) => {
	db.run(`INSERT INTO produse VALUES(?,?,?,?)`, ['1', 'Buletin', '20', '150.25'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		// get the last insert id
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });
	  db.run(`INSERT INTO produse VALUES(?,?,?,?)`, ['2', 'Certificat de nastere', '10', '500.50'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		// get the last insert id
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });
	  db.run(`INSERT INTO produse VALUES(?,?,?,?)`, ['3', 'Cerere de inscriere facultate', '200', '1.99'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		// get the last insert id
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });
	  db.run(`INSERT INTO produse VALUES(?,?,?,?)`, ['4', 'Pasaport', '30', '199.99'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		// get the last insert id
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });
	  db.run(`INSERT INTO produse VALUES(?,?,?,?)`, ['5', 'Certificat de deces', '25', '599.99'], function(err) {
		if (err) {
		  return console.log(err.message);
		}
		// get the last insert id
		console.log(`A row has been inserted with rowid ${this.lastID}`);
	  });
	
	let sql = 'SELECT * FROM produse ORDER BY ID';
	let i = 0;

	req.session.myRows = [];
	req.session.produse = [];

	setTimeout(function(){
		db.all(sql, [], (err, rows) => {
			if (err) {
					throw err;
					}
			rows.forEach((row) => {
				req.session.myRows[i] = [row.ID, row.Nume, row.Stoc, row.Pret];
				console.log(row.Nume);
				i++;
				});
			console.table(req.session.myRows);
			res.redirect('/');
		});
	}, 1000);
	

});

app.post('/adaugare-cos', (req, res) => {

	let idProdusTemp = req.body;
	//console.log(idProdus);
	let idProdus = idProdusTemp.id;
	//console.log(mesaj);
	console.log(idProdus);

	let sql = 'SELECT * FROM produse where ID = ?';
	req.session.indexCos = 0;
	db.each(sql, [idProdus], (err, row) => {
		if (err) {
			throw err;
		}
			//req.session.produse[req.session.indexCos] = [row.ID, row.Nume, 1, row.Pret];
			//req.session.indexCos++;
		//console.table(req.session.produse);

		db.run(`INSERT INTO cos VALUES(?,?,?,?)`, [row.ID, row.Nume, 1, row.Pret], function(err) {
				if (err) {
				  return console.log(err.message);
				}
				// get the last insert id
				console.log(`A row has been inserted in Cos with rowid ${this.lastID}`);
			  });

	})

	res.redirect("/");
});

app.get('/vizualizare-cos', (req, res) => {
	req.session.Cos = [];
	let i = 0;
	let sql = 'SELECT * FROM cos ORDER BY ID';

	db.all(sql, [], (err, rows) => {
		if (err) {
				throw err;
				}
		rows.forEach((row) => {
			req.session.Cos[i] = [row.ID, row.Nume, row.Cantitate, row.Pret];
			console.log(row.Nume);
			i++;
			});
		});
	setTimeout(function(){
		console.table(req.session.Cos);
		res.render('vizualizare-cos', {numeUtilizator: req.session.numeUtilizatorUser, database: req.session.Cos});
	}, 1000);

});

app.get("/admin", (req, res) => {
	res.render('admin', {rolUtilizator: req.session.rolUser, numeUtilizator: req.session.numeUtilizatorUser})
})
app.post("/inserare-una-db", (req, res) => {
	let produs = req.body;
		console.log(produs);
		db.run(`INSERT INTO produse VALUES(?,?,?,?)`, [produs.ID, produs.Nume, produs.Cantitate, produs.Pret], function(err) {
			if (err) {
			  return console.log(err.message);
			}
			console.log(`A row has been inserted in Cos with rowid ${this.lastID}`);
	});

	let sql = 'SELECT * FROM produse ORDER BY ID';
	let i = 0;
	setTimeout(function(){
		db.all(sql, [], (err, rows) => {
			if (err) {
					throw err;
					}
			rows.forEach((row) => {
				req.session.myRows[i] = [row.ID, row.Nume, row.Stoc, row.Pret];
				console.log(row.Nume);
				i++;
				});
			console.table(req.session.myRows);
			res.redirect('/');
		});
	}, 1000);
	
});

app.listen(port, () => console.log(`Serverul rulează la adresa http://localhost:6789`));
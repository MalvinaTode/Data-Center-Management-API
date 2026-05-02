Design Patterns implementate:
1. Singleton Pattern
   - unde?: Database
   - de ce?
   --- Asigura existenta unei singure instante a bazei de date in intreaga aplicatie
   --- Ofera un punct centralizat de acces la date (servere, grupuri, alerte)
   ---  Previne crearea accidentala a mai multor instante care ar putea duce la inconsistente în date
   ---  Controleaza accesul la resursa partajata (baza de date)

2. Command Pattern

 -unde? Implementat prin interfata Command si clasele asociate
    --AddServerCommand
    --AddGroupCommand, FindGroupCommand,RemoveGroupCommand
    --AddMemberCommand, RemoveMemberCommand, FindMemberCommand
    --AddEventCommand
- de ce?

  ---Decupleaza obiectul care invoca operatia (Main) de obiectul care stie cum sa execute operatia (Command)

  ---Permite adaugarea usoara de noi comenzi fara modificarea codului existent

 ---Incapsuleaza toate informatiile necesare pentru executarea unei actiuni (parametri + logica)


3. Factory Pattern

Unde: Implementat in AlertFactory si CommandFactory

De ce:

--Ascunde logica de creare a obiectelor

--Permite adaugarea usoara de noi tipuri de alerte sau comenzi

--Reduce duplicarea codului de instantiere

--Centralizeaza logica de creare a obiectelor

--Ofera flexibilitate in crearea obiectelor fara a expune logica de instantiere

4. Builder Pattern

Unde: Implementat in clasele Server.builder si Location.Builder

De ce:

---Permite constructia obiectelor complexe pas cu pas

---Ofera o modalitate clara si flexibila de a crea obiecte cu multiple campuri optionale

---Evita constructori cu multi parametri

---Asigura validarea datelor in timpul constructiei


5. Observer Pattern

Unde: Implementat prin interfetele Subject, Observer si clasele Server, ResourceGroup

De ce:

---Permite notificarea automata a  grupurilor de monitorizare despre alertele generate de servere

---Permite adaugarea si eliminarea dinamica a observatorilor

---Suporta comunicarea one-to-many intre obiecte

---Respecta principiul Open/Closed prin permiterea adaugarii de noi observatori fara modificarea subiectului

->Main:
verific daca am 2 sau 4 argumente
- pt 2 argumente procesez un singur fisier combinat
- pt4 arg procesez separat fisierele pt servere,grupuri si evnimente

  functia handleOneFile:
  -procesez un fisier de intrare si genereaza fisierul de iesire
  -citesc toate liniile din fisierul de input
  -parcurg linie cu linie fisierul de input
  pt fiecare linie:
  Imparte linia dupa delimiter-ul "|"
   --extrage tipul comenzii (primul element)
   --extrage parametrii comenzii (elementele ramase)
  --creeaza comanda corespunzatoare prin factory
  --executa comanda cu parametrii si numarul liniei

->Database
--gestioneaza toate datele aplicatiei - servere, grupuri, alerte

--getInstance() - returneaza instanta unica a bazei de date
--addServer(), addResourceGroup(), addAlert() - adauga entitati in colectiile respective
--getServers(), getResourceGroups(), getAlerts() - returneaza colectiile pentru procesare


  ->Server
  --reprezinta un server cu campuri obligatorii (IP, location, owner) si optionali (hostname, status, CPU, RAM, storage)

--Builder - cls interna pentru constructia serverului
--attach()/detach() - gestioneaza lista de observatori 
--notifyObservers() - notifica toate grupurile atunci cand se genereaza o alerta
--generateAlert() - metoda apelata de AddEventCommand pentru a distribui alerta

->Location
--reprezinta o locatie cu tara si campuri optionale

->ResourceGroup.java
--reprezinta un grup de monitorizare asociat unui server

addMember()/removeMember() - gestioneaza membrii grupului
update() - implementeaza interfata Observer, primeste notificari despre alerte de la server

      Comenzi implementate:
      
   AddServerCommand

--extrage parametrii (IP, country, user name/role, status)
--valideaza datele (arunca MissingIpAddressException, UserException, LocationException)
--construieste Server cu Builder si il adauga in baza de date

   AddGroupCommand

-creeaza un ResourceGroup pentru un IP
-gaseste serverul corespunzator si ataseaza grupul ca observer

   AddEventCommand

-extrage tipul, severitatea, IP-ul si mesajul alertei
-creeaza alerta prin AlertFactory
-gaseste serverul si apeleaza generateAlert() pentru a notifica grupurile

   FindGroupCommand / RemoveGroupCommand

-cauta/sterge grupuri dupa IP
-afiseaza mesajele coresp

   AddMemberCommand / FindMemberCommand / RemoveMemberCommand

-gstioneaza membrii din grupuri
-cauta grupul dupa IP, apoi adauga/cauta/sterge membrul pe baza name si role

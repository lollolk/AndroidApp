**Our Android App for Software Engineering 3 at Reutlingen University, Germany.**

**Notes:**

- We are/were using these technologies and agile methods: XP (agile), GitHub, Eclipse, [Android Emulator x86](https://sites.google.com/a/android-x86.org/web/), standard emulator (just fuck it), test devices
- Developed for Android 4.1 (min)

# Development process: #

**Create PHP site to set & save GEO POI.**

- [Bootstrap](http://getbootstrap.com) for easy design, [php](http://php.net) for backend to connect to John Malc's own MySQL server. 
- Make it reliable and make sure it works. 
- Problem: MySQL saves only "1" instead of storing numbers I want. Therefore I asked a question here: [http://stackoverflow.com/questions/19465195/mysql-stores-only-1-as-a-value-no-matter-what-numbers-i-submit-in-the-form](http://stackoverflow.com/questions/19465195/mysql-stores-only-1-as-a-value-no-matter-what-numbers-i-submit-in-the-form) -> Resolved
- After a connection to the server, we save POI as double in the DB and print them on same page. Next day: Done.
- After migration to my personal server, make absolutely sure it works fine.

**Make connection to android**

- Use http library to connect to the server and get all the POI
- ....

3. Present to teacher
4. Drink

**Progress of the Android Application**

1. Project SE_3 (Clean Code from Github) + SmartGit crashed my Eclipse several times.
2. Created a new Project SEMaps. I will add this Project to Github and SmartGit as soon as possible.
3. SEMaps works like a charm. It displays Google Map AND the current Position via GPS.
4. Reading some documentary..

**What we created and how should user use it**

1. You go on web-app and insert some POI.
2. ......
3. ....


**Our official Tasks**

![Tasks](/aufgabe.JPG)

**MIT-License**

-"We don't care"

**Developers**:

- John Malc ([@malcjohn on twitter](https://twitter.com/malcjohn)) - developed web-app (used on his own server)
- A.B.
- Metin 
- H.R.
- T.W.



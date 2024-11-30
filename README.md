<h1>Journal App With JWT Authentication Using Spring Security</h1>

<p>This project is a collection of REST APIs adhering to the basic backend structure of any application. Included in the package are industry standards like Authentication, Authorization, Role Based Access Control (RBAC) and enhaced security using JWT</p>
<hr>
<h3>Specifications/Packages</h3>
<ul>
  <li>Java 23</li>
  <li>SpringBoot 3</li>
  <li>MongoDB Atlas</li>
  <li>Spring Security 6</li>
  <li>lombok</li>
  <li>JsonWebToken (JWT)</li>
  <li>Password Hashing</li>
  <li>User login/signup endpoints</li>
  <li>IntelliJ IDEA</li>
</ul>
<hr>
<h3>Summary</h3>
<p>This project included all the necessary endpoints for a user-based app that requires login, signup, logout functionality. Role Based Access Control endpoints are in place using common roles like User, Moderator and Admin.</p>
<p>Each role has it's own controller but uses a shared service based or type of resource.</p>
<hr>
<h3>Project Details</h3>
<h5>User Roles:</h5>
<ul>
  <li>USER</li>
  <li>MOD</li>
  <li>ADMIN</li>
</ul>
<img src="https://github.com/user-attachments/assets/4aff7ac0-b655-4182-8f63-edf1387651cc" width="50%">

<H5>Controllers</H5>
<ul>
  <li>Journal Controller</li>
  <li>User Controller</li>
  <li>Moderator Controller</li>
  <li>Admin Controller</li>
  <li>Public Controller (open to everybody)</li>
</ul>

<h5>Services</h5>
<ul>
  <li>User Service</li>
  <li>Journal Service</li>
  <li>User Details Service Implementation (Spring Security Feature)</li>
</ul>

<hr>

<h3>Basic Working</h3>
<h5>User logs in with the public controller</h5>
![image](https://github.com/user-attachments/assets/e103e644-10e7-4809-9f9c-244d2daa3bdb)
<p>The application receives a JWT token and authenticated using the token until it is expired.</p>

<h5>Access to Journals</h5>
<p>Once the user is authenticated, they can view their own journals but not the others</p>
![image](https://github.com/user-attachments/assets/309cd3ff-76af-4c93-90b1-f084977f6a28)

<h5>The user can then create, delete and update any journal associated to them</h5>
<strong>Refer to journalController</strong>

<h3>Mod vs Admin</h3>
<p>Moderators and admins are higher privileged users with extra capabilties</p>
<h4>Moderator featuers</h4>
<ul>
  <li>Delete any journal from any user that might not be appropriate</li>
  <li>Disable/enable users based on their behavior on the application</li>
</ul>

<h4>Admin features</h4>
<ul>
  <li>Get the list of all users on the applications</li>
  <li>Create more admins</li>
  <li>Delete any other user (not same as disabling)</li>
</ul>

<hr>

<h3>Spring Security and definition</h3>
<p>Spring Security is a Java/Java EE framework that provides authentication, authorization and other security features for enterprise applications. </p>
<h5>Use of Spring Security</h5>
![image](https://github.com/user-attachments/assets/17dd744e-7235-4dfb-82df-ec04377772c2)

<p>We also have implemented password hashing in our application which is managed in the same SpringSecurity configuration</p>

<hr>

<h3>Database and Connection</h3>
<p>We have used MongoDB for our database storage. The connections are created using MongoDB Atlas and application.properties file in our application. Once the connection gets successful, Spring automatically maps the data to the repositories that we have created.</p>
![image](https://github.com/user-attachments/assets/5516b8c2-d51e-4142-b467-2cd730caabaa)

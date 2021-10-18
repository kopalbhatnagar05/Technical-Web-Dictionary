Technical Dictionary App
Submitted by Kopal Bhatnagar


This Technical Web Dictionary is implemented by React +Spring Boot (REST ) frameworks.
Scope of the project:

Functionalities:

The project gives the functionality to the users who are divided into:
1.	Regular user (searching and displaying)
2.	Administrator (all functionalities)
(This is achieved by assigning roles via Spring Security and permission is given based on that. Additionally, sessionStorage is used for session handling)

Such as:
1.	Adding the word along with description and reference images (maximum 6 and minimum 2)
a.	Displaying those images in an image slider using carousel by react-slick (to install npm install react-slick –save). on the front end.

b.	Necessary Error Handling:  Raises error for unauthorized access or if the word already exists on the front end.

c.	Details are sent as formdata object with images as an multipartfile array via the API call. All images are saved in a folder created dynamically named after the title of the word (stored in techdict/public/images relative to react app)  Only the relative path to the folder is stored in the database. 


2.	Editing the word details such as description and images
a.	Title is serving as an id in most cases hence it cannot be edited, images can be added up to 6 and if the images go above the required number (6), the oldest images are deleted to make space.
b.	Necessary Error Handling:  Raises error for unauthorized access.

3.	Deleting the word entirely, there is a button associated with every entry and upon clicking on it, it deletes the entire data from the database.
a.	Images are deleted along with the directory upon deleting the word.
b.	Necessary Error Handling:  Raises error for unauthorized access or if the word already exists on the front end.

4.	Searching the word, using title as the id, shows all the details of the word.
a.	Displaying the images in an image slider using carousel by react-slick (to install npm install react-slick –save). on the front end.
5.	Displaying all the words, in a list alphabetically sorted as links.
a.	Upon clicking a word, it renders the details of the word in a fresh page.
6.	Seeing all users registered, all admins can see registered users and their roles.
a.	Necessary Error Handling:  Raises error for unauthorized access or if the word already exists on the front end.
7.	Register:
a.	Register-Admin: Registering a user and assigning the role of ADMIN.
b.	Register-User: Registering a user and assigning the role of USER.

Image Folder Structure: 
Images on upload passed to api  dynamically stored in /images/<title>/image.jpg (directories are created if don’t exist)
 





 

•	For seamless working of project on another machine  please change the variable value of the 
Class variable called: ‘base url’ in the FileUpload.java (located in com.techdict.app.helper package) to the value where the whole project resides.
 
The reason this method was approached because Chrome/IE do not support local resource retrieving, images would have to be stored in a cloud server, which could not be achieved in this duration of time

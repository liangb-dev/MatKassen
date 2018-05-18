# MatKassen
Recipe organizer

Just a little project I started to organize recipes from the meal 
delivery service "Veganska Matkassen" by Sanne Venlov. 
The original recipes come with ingredients and steps for 4 dishes 
a week. I'm just trying to record the recipes digitally, and parse 
the date for easy retrieval of content, such as Title, Ingredients, 
Steps. 
I also intend to make a method that merges the weekly ingredients, 
so as to make shopping weekly grocery easy. 

The recipies are transcribed into .txt files that are organzied into
folders organized by corresponding "Week". These .txt files are read
by the java program, and its content are parsed by my code into 
respective fields for easy retrieval. Each .txt file corresponds to 
a Day (which contains one recipe), and for each a Day-object is 
created in wich data is stored. 

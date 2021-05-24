# Tablature Converter
It is for converting tablature guitar text score to music xml form to play in application that supports music xml.

### 1. Put guitar tab text file.
#### e.g) ![image](https://user-images.githubusercontent.com/82852354/119384662-0c72da00-bc93-11eb-8f60-ac261bbe928e.png)
#### If the file through the path does not exist or file is empty, try again with proper file.
![image](https://user-images.githubusercontent.com/82852354/119386901-2e219080-bc96-11eb-999a-c1b4a564c124.png)
###  ----
### 2. Valid notes
#### For now, Supported notations:
#### /  slide up
#### \  slide down
#### h  hammer-on
#### p  pull-off
#### 0  normal integer note
#### If any other notation is contained inside score, the file's not converted.
###  ----
### 3. Save the converted file
#### After converting, put file name and path where you want to save it.
![image](https://user-images.githubusercontent.com/82852354/119389761-0b917680-bc9a-11eb-9b89-d1a18663fbff.png)
![image](https://user-images.githubusercontent.com/82852354/119389903-41365f80-bc9a-11eb-9943-a250ba3eabd9.png)
#### If the file through the path is not proper or there is file that has the same name in directory, try again with proper path and different file names.


e.g  
|-----1-----------|--------------------|  
|-----2-----------|--------------------|  
|-----3-----------|--------------1\3---|  
|--------0--------|--------3h5---------|  
|----------4------|---3----------------|  
|------------5----|---0----------------|  
sample  

result
![image](https://user-images.githubusercontent.com/82852354/119395871-1d771780-bca2-11eb-9311-54dd1401f6a7.png)
run by MuseScore3

#### *Constraints
##### All measures should have the same length.
##### All measures should have 6 lines which is the number of guitar's string

##### ++Need to do update : the case that rest note is in front of the measure

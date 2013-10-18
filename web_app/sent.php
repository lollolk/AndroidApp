 <?php
	$con=mysqli_connect("komentovaneudalosti.cz","android_app","android");

	#Check connection
        if (mysqli_connect_errno()) {
            echo 'Database connection error: ' . mysqli_connect_error();
            exit();
        }
http://www.tutorialspoint.com/php/mysql_insert_php.htm
http://stackoverflow.com/questions/14643758/how-to-save-the-generated-html-from-wysihtml5-or-pass-it-to-php-page
http://forum.xda-developers.com/showthread.php?t=2325799
	#Escape special characters to avoid SQL injection attacks
        $firstname = mysqli_real_escape_string($con, $firstname);
        
        #Query the database to get the user details.
        $userdetails = mysqli_query($con, "SELECT `latitude`, `longitude` FROM `android`");

        #If no data was returned, check for any SQL errors
        if (!$userdetails) {
            echo 'Could not run query: ' . mysqli_error($con);
            exit;
        }

        #Get the first row of the results
        $row = mysqli_fetch_row($userdetails);

        #Build the result array (Assign keys to the values)
        $result_data = array(
            'FirstName' => $row[1],
            'LastName' => $row[2],
            'Age' => $row[3],
            'Points' => $row[4],
            );

        #Output the JSON data
        echo json_encode($result_data); 
        
            $con=mysqli_connect('komentovaneudalosti.cz','android_app','android');

            if (mysqli_connect_errno()){
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
            }

            $lon = $_POST['inputLongitude'];
            $lat = $_POST['inputLatitude'];

            mysqli_query($con,"INSERT INTO android (id, latitude, longitude)
                                                VALUES (9, '$lat', '$lon') ");
//          mysql_close($con);
?>

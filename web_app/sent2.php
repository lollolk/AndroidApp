<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Android WebApp</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta charset="utf-8">
        <meta name="description" content="Android Web App, see our GitHub">
        <link rel="stylesheet" href="http://bootswatch.com/readable/bootstrap.min.css"> <!-- http://bootswatch.com/ -->
        <link rel="stylesheet" href="css/bootstrap.min.css"> 
        <link rel="stylesheet" href="css/jquery.pnotify.default.css"> 
        <link rel="stylesheet" href="css/styles.css">
        <script src="http://code.jquery.com/jquery-1.10.1.min.js" ></script> 
        <script src="js/bootstrap.min.js"></script>
        <script src="js/maplace.min.js"></script>
        <script src="js/jquery.pnotify.min.js"></script>
        <link rel="shortcut icon" href="images/icon.png"> 
    </head> 

    <?php      
    //Connection to the server
                $conn = mysqli_connect('127.0.0.1', 'komentovaneud003', 'android_reut', 'komentovaneudalosticz02') or die("Failed #1");
                // if some error 
                if (mysqli_connect_errno()){
                    echo "Failed to connect to MySQL: " . mysqli_connect_error();
                }

                // http://diskuse.jakpsatweb.cz/?action=vthread&forum=9&topic=145971 We need isset(), but also
                // http://stackoverflow.com/questions/19465195/mysql-stores-only-1-as-a-value-no-matter-what-numbers-i-submit-in-the-form
                $safe_lati = mysqli_real_escape_string($conn, $_POST['inputLatitude']);
                $safe_longti = mysqli_real_escape_string($conn, $_POST['inputLongitude']);
                $safe_titel = mysqli_real_escape_string($conn, $_POST['inputTitel']);
                $safe_snippets = mysqli_real_escape_string($conn, $_POST['inputSnippets']);
                // saves varibles with help of SQL
                $sql = "INSERT INTO `komentovaneudalosticz02`(`key_prim`, `lati`, `longti`,`titel`, `snippets`)
                VALUES (`key_prim`,$safe_lati,$safe_longti,'$safe_titel','$safe_snippets')";
                mysqli_query($conn,$sql);
    ?>

    <body>
        <div class="row">
            <div class="col-xs-6">    
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Primary Key in DB</th>
                            <th>Longitude</th>
                            <th>Latitude</th>
                            <th>Titel</th>
                            <th>Snippets</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php
                            $cn = mysqli_connect('127.0.0.1', 'komentovaneud003', 'android_reut', 'komentovaneudalosticz02') or die("Failed #2");
                            // It fetches results from DB through a SQL request
                            $q = "SELECT `key_prim`, `lati`, `longti`, `titel`, `snippets` FROM `komentovaneudalosticz02`";
                            $results = mysqli_query($cn, $q) or die(mysqli_error($cn));
                            $json = array();
                            // It fetches results from db and stores them in a row -> prints them
                            // http://stackoverflow.com/questions/2974011/while-row-mysql-fetch-arrayresult-how-many-loops-are-being-performed
                            while ($row = mysqli_fetch_array($results)){
                                echo "<tr>
                                <td>".$row['key_prim']."</td>
                                <td>".$row['longti']."</td>
                                <td>".$row['lati']."</td>
                                <td>".$row['titel']."</td>
                                <td>".$row['snippets']."</td>
                                </tr>";                             
                            }
                        ?>
                    </tbody>
                </table>
            </div>
            <div class="col-xs-6">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Json</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php
                            $cn = mysqli_connect('127.0.0.1', 'komentovaneud003', 'android_reut', 'komentovaneudalosticz02') or die("Failed #2");
                            // It prints the results from DB through a SQL request
                            $q = "SELECT `key_prim`, `lati`, `longti`, `titel`, `snippets` FROM `komentovaneudalosticz02`";
                            $results = mysqli_query($cn, $q) or die(mysqli_error($cn));
                            // no need probably
                            $json = array();
                            // http://www.akawebdesign.com/2009/08/18/json-formatting-in-php/
                            $jsonResponse = array("data" => array());

                            while ($row = mysqli_fetch_array($results)){
                                $json["key_prim"] = $row["key_prim"];
                                $json["lati"] = $row["lati"];
                                $json["longti"] = $row["longti"];
                                $json["titel"] = $row["titel"];
                                $json["snippets"] = $row["snippets"];

                                // create data array    
                                $named_array = array(
                                           "key_prim" => $row['key_prim'],
                                           "lati" => $row['lati'],
                                           "longti" => $row['longti'], 
                                           "titel" => $row['titel'],
                                           "snippets" => $row['snippets']
                                );
                                // encode named array in a pretty form and save
                                $named_array_json = json_encode($named_array);
                                // behave such as stack; pushes the passed variables onto the end 
                                // http://php.net/manual/en/function.array-push.php
                                array_push($jsonResponse['data'], $named_array);

                            }
                            $json_saved_response = json_encode($jsonResponse, JSON_PRETTY_PRINT);
                            // print named array json in pritty. 
                            echo "<td><pre>".json_encode($jsonResponse, JSON_PRETTY_PRINT)."</pre></td>";
                            // create json.json file which is generated on the fly from json saved variable
                            $fp = fopen('json.json', 'w');
                            fwrite($fp, $json_saved_response);
                            fclose($fp); 
                        ?>
                    </tbody>    
                </table>
            </div>
        </div>
    </body>
</html>


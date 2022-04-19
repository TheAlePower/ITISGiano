<?php
//Horribly written by Inari

function timeToDate($value) {
    $seconds = $value;
    $minutes = 0;
    $hours = 0;
    while ($seconds > 59) {
        $minutes++;
        $seconds -= 60;
    }
    while ($minutes > 59) {
        $hours++;
        $minutes -= 60;
    }
    return [$hours,$minutes,$seconds];
}

function formatTime($time,$separator) {
    return ($time[0]>9?$time[0]:"0".$time[0])
        .$separator.($time[1]>9?$time[1]:"0".$time[1])
        .$separator.($time[2]>9?$time[2]:"0".$time[2]);
}

echo 'Leggendo il file ' . htmlspecialchars($_GET["file"]);

// Parametri dal web
$start = (int) $_GET["start"];
$stop = (int) $_GET["stop"];
//
$splitArgs = str_split($_GET["file"]);

$startTime = timeToDate($start);
$stopTime = timeToDate($stop);

echo '<br>';
echo 'Valori dalle '.formatTime($startTime,":");
echo ' alle '.formatTime($stopTime,":");
echo ' del giorno '.$splitArgs[2].$splitArgs[3]."/".$splitArgs[4].$splitArgs[5]."/".$splitArgs[6].$splitArgs[7];

$rx1 = array();
$rx2 = array();
$sdrx1 = array();
$sdrx2 = array();

$att = array();

$anomalia = array();

$xml = new SimpleXMLElement(file_get_contents("./".htmlspecialchars($_GET["file"])));


for ($i = $start; $i < $stop;$i++) {
    array_push($rx1,$xml->Value[$i]->intensRX1);
    array_push($rx2,$xml->Value[$i]->intensRX2);
    array_push($sdrx1,$xml->Value[$i]->SDRX1);
    array_push($sdrx2,$xml->Value[$i]->SDRX2);
    array_push($att,$xml->Value[$i]->Att);

    array_push($anomalia,($xml->Value[$i]->flagV1 == 'true'?0:1) + ($xml->Value[$i]->flagV2 == 'true'?0:1) + ($xml->Value[$i]->flagV3 == 'true'?0:1) + ($xml->Value[$i]->flagV4 == 'true'?0:1) + ($xml->Value[$i]->flagV5 == 'true'?0:1));
}

?>

<html>
<head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var RX1 = [<?php echo '"'.implode('","',  $rx1 ).'"' ?>];
            var RX2 = [<?php echo '"'.implode('","',  $rx2 ).'"' ?>];
            var SDRX1 = [<?php echo '"'.implode('","',  $sdrx1 ).'"' ?>];
            var SDRX2 = [<?php echo '"'.implode('","',  $sdrx1 ).'"' ?>];
            var Att = [<?php echo '"'.implode('","',  $att ).'"' ?>];
            var anomalia = [<?php echo '"'.implode('","',  $anomalia ).'"' ?>];

            var data = new google.visualization.DataTable();


            data.addColumn('number','Time');
            data.addColumn('number','RX1');
            data.addColumn('number','RX2');
            data.addColumn('number','SDRX1');
            data.addColumn('number','SDRX2');
            data.addColumn('number','Att');


            for (let i = 0; i < RX1.length; i++) {
                data.addRow([i
                    ,parseFloat(RX1[i])
                    ,parseFloat(RX2[i])
                    ,parseFloat(SDRX1[i])
                    ,parseFloat(SDRX2[i])
                    ,parseFloat(Att[i])]
                    //,parseFloat(anomalia[i])
                );
            }

            var options = {
                title: 'Rilevamento sensori',
                //curveType: 'function',
                legend: { position: 'bottom' }
            };

            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

            chart.draw(data, options);
        }
    </script>
</head>
<body>
<div id="curve_chart" style="width: 1400px; height: 500px"></div>
</body>
</html>
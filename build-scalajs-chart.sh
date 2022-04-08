echo 'var providedBenchmarks = ["Chrome", "Edge", "Firefox", "Opera"];
var providedBenchmarkStore = {
"Chrome":'
jq -c '[ .[] | select( .benchmark | (test("Reading.circe(Jawn)?$"))) ]' < chrome.json
echo ', "Edge":'
jq -c '[ .[] | select( .benchmark | (test("Reading.circe(Jawn)?$"))) ]' < edge.json
echo ', "Firefox":'
jq -c '[ .[] | select( .benchmark | (test("Reading.circe(Jawn)?$"))) ]' < firefox.json
echo ', "Opera":'
jq -c '[ .[] | select( .benchmark | (test("Reading.circe(Jawn)?$"))) ]' < opera.json
echo '}'

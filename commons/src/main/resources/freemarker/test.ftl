<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<h1>
    Welcome ${user!"visitor"}<#if user??><#if user == "Big Joe">, our beloved leader</#if></#if>!
</h1>
<p>Our latest product:
    <a href="${latestProduct.url}">${latestProduct.name}</a>!
</body>
</html>
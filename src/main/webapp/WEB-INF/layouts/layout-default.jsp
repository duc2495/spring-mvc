<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title><tiles:getAsString name="title" /></title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
</head>

<body>

	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="footer" />

</body>
</html>

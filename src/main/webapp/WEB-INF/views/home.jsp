<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>


<script src='<s:url value="resources/js/appjs.js"></s:url>'></script>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<script
	src='<s:url value="/resources/imagedist/js/vendor/jquery.ui.widget.js"></s:url>'></script>
<script
	src='<s:url value="/resources/imagedist/js/jquery.iframe-transport.js"></s:url>'></script>
<script src='<s:url value="/resources/imagedist/js/jquery.fileupload.js"></s:url>'></script>

<script src='<s:url value="/resources/js/myuploadfunction.js"></s:url>'></script>



</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-4" align="center">
				<h1 align="center">KATEGORI EKLEME</h1>
				<div class="card" style="width: 20rem;">
					<div class="card-body">
						<div class="form-group">
							<label for="kadi">Kategori Ekle</label> <input type="text"
								class="form-control" id="kadi" name="kadi"
								placeholder="Kategori Giriniz...">
						</div>
						<button id="btnKatEkle" class="btn btn-primary">EKLE</button>
					</div>
				</div>
			</div>
			<div class="col-sm-8" align="center">
				<h1 align="center">URUN EKLEME</h1>
				<div class="card" style="width: 45rem;">
					<div class="card-body">
						<div class="form-group">
							<label for="uadi">Urun Adi</label> <input type="text"
								class="form-control" id="uadi" name="uadi"
								placeholder="Urun Giriniz...">
						</div>
						<div class="form-group">
							<label for="ukid">Kategori</label> <select name="ukid" id="ukid"
								class="form-control">
								<c:if test="${ not empty katls  }">
									<c:forEach var="item" items="${ katls }">
										<option value="${ item.getKid() }">${item.getKadi()}
										</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
						<div class="form-group">
							<label for="uadi">Urun Resimi:</label> <input id="fileupload"
								type="file" name="files[]" data-url="admin/uploadmi" multiple>
						</div>
						<button id="btnUrunEkle" class="btn btn-primary">URUN
							EKLE</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<h1 align="center">URUN TABLOSU</h1>
				<table class="table">
					<thead class="thead-light">
						<tr>
							<th scope="col">ID</th>
							<th scope="col">ADI</th>
							<th scope="col">KATEGORI</th>
							<th scope="col">RESIM</th>
							<th scope="col">
								<form action='<s:url value="/api/jsongit"></s:url>'
									method="post">
									<input type="submit" class="btn btn-outline-primary btn-sm"
										value="JSON'A GIT">
								</form>
							</th>
						</tr>
					</thead>
					<tbody id="tablo">
						<c:if test="${ not empty urunls  }">
							<c:forEach var="item" items="${ urunls }">
								<tr id="trUrun${ item.getUid() }">
									<th scope="row">${ item.getUid() }</th>
									<td>${ item.getUadi() }</td>
									<td>${ item.getKadi() }</td>
									<td><img
										src='http://localhost:8080/UrunResimleri/${ item.getUresim() }'
										width='100' /></td>
									<td>
										<button onclick="urunSil(${ item.getUid() })" type="button"
											class="btn btn-outline-danger btn-sm">SIL</button>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>




</body>
</html>

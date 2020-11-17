<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.Account"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Banco UTN - Cuenta <%=request.getParameter("p")%></title>

<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>
<body id="page-top">
	<div id="wrapper">

		<!-- Sidebar -->
		<!-- INCLUYO EL MENÃš ACÃ -->
		<jsp:include page="MenuAdmin.html"></jsp:include>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">
						<div class="topbar-divider d-none d-sm-block"></div>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">André
									Villalta</span> <img class="img-profile rounded-circle"
								src="img/profile.png">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									Settings
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800"><%=request.getParameter("p")%>
							Cuenta
						</h1>
					</div>

					<form class="needs-validation" method="post"
						action="ServletsCuentas">
						<div class="form-row">
							<div class="form-group col-md-4">
								<label for="title">DNI</label> <input class="form-control"
									name="txtDNI" required="required" type="number"
									placeholder="Ingrese DNI" value="${DNI}"> <input
									type="hidden" name="parameter"
									value="<%=request.getParameter("p")%>">
							</div>
							<div class="form-group col-md-1 mt-1">
								<button type="submit" name="BuscarExistencia"
									class="btn btn-warning btn-circle mt-4">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</div>
						<%
							if (request.getAttribute("estadoExistencia") != null) {
								boolean estadoM = (Boolean) request.getAttribute("estadoExistencia");

								if (estadoM == true) {
									if (request.getParameter("p").equals("Alta")) {
						%>
						<label for="title">${Nombre}</label>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="title">CBU</label> <input class="form-control"
									maxlength="22" name="txtCBU" value="${CBU}" type="text"
									placeholder="Nº identidad + NªSucursal + Dígito identificador + Numero de cuenta + Dígito verificador">
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-3">
								<label for="title">Tipo de cuenta</label> <select
									class="custom-select" name="tipoCta" id="inputGroupSelect02">
									<option selected>Elija opción</option>
									<option value="1">Caja de ahorro</option>
									<option value="2">Cuenta corriente</option>
								</select>
							</div>

							<div class="form-group col-md-3">
								<label for="title">Saldo inicial ($ - pesos argentinos)</label>
								<input class="form-control" name="txtSaldo" type="text"
									value="10000" placeholder="Saldo inicial">
							</div>
						</div>
						<%
							} else {
						%>
						<div class="form-row">
							<div class="form-group col-md-3">
								<label for="title">Seleccione cuenta a dar de baja</label> <select
									class="custom-select" name="NroCuentaBaja"
									id="inputGroupSelect03">
									<option selected>Elija Cuenta</option>
									<%
										if (request.getAttribute("CuentaB1").toString().length() > 0) {
									%>
									<option value="${CuentaB1}">Nro: ${CuentaB1}</option>
									<%
										}
													if (request.getAttribute("CuentaB2").toString().length() > 0) {
									%>
									<option value="${CuentaB2}">Nro: ${CuentaB2}</option>
									<%
										}
													if (request.getAttribute("CuentaB3").toString().length() > 0) {
									%>
									<option value="${CuentaB3}">Nro: ${CuentaB3}</option>
									<%
										}
									%>
								</select>
							</div>
						</div>
						<%
							}
						%>
						<div class="form-row">
							<div class="form-group col-md-3">
								<button type="submit" name="btnGestionarCuenta"
									class="btn btn-primary mt-2">
									Dar de
									<%=request.getParameter("p")%></button>
							</div>
						</div>

					</form>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->
			<%
				}
					if (request.getAttribute("logearError") != null) {
			%><script>
				alert("${logearError}");
			</script>
			<%
				}
				}
			%>
			<%
				if (request.getAttribute("DadaBaja") != null) {
			%><script>
				alert("Cuenta dada de Baja correctamente");
			</script>
			<%
				}
			%>
			<%
				if (request.getAttribute("DadaAlta") != null) {
			%><script>
				alert("Cuenta dada de Alta correctamente");
			</script>
			<%
				}
			%>
			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Banco UTN 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->
		</div>
		<!-- End of Content Wrapper -->
	</div>

	<!-- End of Page Wrapper -->

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>
</body>
</html>

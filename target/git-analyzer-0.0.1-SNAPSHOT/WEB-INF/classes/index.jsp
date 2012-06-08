<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>

<html>
	<head>
		<title>#Repository name</title>
		
		<!-- Style -->
		<link rel="stylesheet" href="static/style.css" media="all" />
		
		<!-- JavaScripts -->
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
		<script type="text/javascript" src="static/scripts/highcharts.2.2.4.js"></script>
		<script type="text/javascript" src="static/scripts/highstock.1.1.5.js"></script>
		<script type="text/javascript" src="static/scripts/jquery.tablesorter.js"></script>
		<script type="text/javascript" src="static/scripts/charts.js"></script>
	</head>
	
	<body>
		<div id="page">
			<!-- Commits -->
			<h1>Commits by date</h1>
			<div id="chart-commits" class="chart"><!-- Generated chart --></div>
			
			<div class="column">
				<h3>Top commits day</h3>
				<table id="commits" class="sortable">
					<thead>
						<tr>
							<th class="commits-date sort-date">Date</th>
							<th class="commits-count sort-numeroc">Commits</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${statistics.getCounter('top_commits_by_date')}" var="commit" varStatus="status">
							<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
								<td class="commits-date">${commit.key}</td>
								<td class="commits-count">${commit.value}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<!-- Users -->
			<h1>Users in repository</h1>
			<table id="users" class="sortable paginated">
				<thead>
					<tr>
						<th class="user-name sort-alpha">Name</th>
						<th class="user-email sort-alpha">Email</th>
						<th class="user-authored sort-numeric">Commits as author</th>
						<th class="user-committed sort-numeric">Commits as committer</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${statistics.getCounter('commits_by_user')}" var="user" varStatus="status">
						<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
							<td class="user-name">${user.key.name}</td>
							<td class="user-email"><a href="mailto:${user.key.email}">${user.key.email}</a></td>
							<td class="user-authored">${user.value.authored}</td>
							<td class="user-committed">${user.value.committed}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<script type="text/javascript">
			$(document).ready(function() {
				// Commits by time
				var commits = [];
	    		<c:forEach items="${statistics.getCounter('commits_by_date')}" var="commit">
	    			commits.push(${commit.value});
				</c:forEach>

				createTimeChart("chart-commits", [{
					name: 'Commits',
					data: commits,
					pointInterval: 24 * 60 * 60 * 1000
				}]);
			});
		</script>
	</body>
</html>
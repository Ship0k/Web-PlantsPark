<%@ page import="by.park.entity.object.Plant" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
    <title>Plants</title>
    <link rel="shortcut icon" href="images/favicon.png" type="image/png">
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
  <%
    Plant plant = (Plant) request.getAttribute("curplant");
  %>
  <h2>Plant information</h2>
  <form id="mainform" name="mainform" action="" method="post">
    <table border="1" width="60%">
      <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Landing date</th>
        <th>Artworks</th>
        <th>Treatments</th>
        <th>Destruction date</th>
      </tr>
      <tr>
        <td><%=plant.getId()%></td>
        <td><%=plant.getTitle()%></td>
        <td><%=plant.getPlantDetails().getLandingData()%></td>
        <td><%=plant.getPlantDetails().getArtWorkN()%></td>
        <td><%=plant.getPlantDetails().getTreatmentN()%></td>
        <td><%=plant.getPlantDetails().getDestructionDate()%></td>
      </tr>
    </table>
    <br>
    <h3>Enter id to go quickly:
    <input type="text" name="id" size="6">
    <input class="ok" type="button" value="OK" onclick="getServlet('/byId')">
    </h3><br>
    <input class="ll" type="button" value="Prev" onclick="getServlet('/prev')">
    <input class="ll" type="button" value="Insert" onclick="getServlet('/insert')">
    <input class="dl" type="button" value="Delete" onclick="confirmComplete('/delete')">
    <input class="ll" type="button" value="Rename" onclick="getServlet('/update')">
    <input class="ll" type="button" value="Landing" title="if there is a landing date" onclick="confirmComplete('/landing')">
    <input class="ll" type="button" value="Art" title="if planted and not destroyed" onclick="confirmComplete('/art')">
    <input class="ll" type="button" value="Treatment" title="if planted and not destroyed" onclick="confirmComplete('/treatment')">
    <input class="ll" type="button" value="Destruction" title="if planted and not destroyed" onclick="confirmComplete('/destruction')">
    <input class="ll" type="button" value="Next" onclick="getServlet('/next')">
    <br>
  </form>
  </body>
</html>

<script>
  function getServlet(srvUrl) {
    var form = document.getElementById("mainform");
    form.action = srvUrl;
    form.submit();
  }

  function confirmComplete(srvUrl) {
      var answer = confirm("Are you sure you want to continue");
      if (answer == true) {
          return getServlet(srvUrl);
      }else {
          return false;
      }
  }
</script>
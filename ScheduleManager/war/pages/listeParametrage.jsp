<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/WEB-INF/Taglibs.tld" prefix="t" %>

	<div class="fond_chemin" id="chemin">
		&nbsp;
	</div>
	<div class="fond_corps" id="corps">
		<table id="dataTable">
			<thead>
				<tr>
					<th><t:get name="table.title.lieux" /></th>
					<th>titre2</th>
				</tr>
			</thead>
			<tbody>
				<tr class="odd">
					<td><a href="/lieu_list"><img src="../images/lieu.png" /></a></td>
					<td>titre2</td>
				</tr>
				<tr class="even">
					<td></td><td></td>
				</tr>
			</tbody>
		</table>

	</div>
	<script>
		function modifierContact(id) {
			window.location.href = "/contact_modif?idContact=" + id;
		}
		function ajouterContact() {
			window.location.href = "/contact_add";
		}
	</script>
package fr.eemcs.schedulemanager.decorator;

import org.displaytag.decorator.TableDecorator;

import fr.eemcs.schedulemanager.entity.ArticleVO;
import fr.eemcs.schedulemanager.helper.FormatHelper;

public class ArticleDecorator extends TableDecorator {
	public String getDateCreationArticle() {
		ArticleVO article = (ArticleVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(FormatHelper.formatDate(article.getDateCreationArticle(), "dd/MM/yyyy"));
	}
	
	public String getAuteur() {
		ArticleVO article = (ArticleVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(article.getAuteur());
	}
	
	public String getDescription() {
		ArticleVO article = (ArticleVO)getCurrentRowObject();
		return FormatHelper.addPaddingToTD(article.getDescription());
	}
	
	public String getActions() {
		ArticleVO article = (ArticleVO)getCurrentRowObject();
		String actions = "";
		
		actions += "<a href=\"javascript:modifierArticle(" + article.getId() + ");\"><img height=\"25px\" src=\"/images/modifierArticle.png\" /></a>";
		actions += "<a href=\"javascript:supprimerArticle(" + article.getId() + ");\"><img height=\"25px\" src=\"/images/supprimerArticle.png\" /></a>";
		
		return FormatHelper.addPaddingToTD(actions);
	}
}

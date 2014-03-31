package br.com.notasfiscais.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.notasfiscais.util.UsuarioLogado;

public class ValidatorUserLoggedListener implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioLogado usuarioLogado;

    /*
     * TODO- the pages authorized for user logged in is possible retrieve in
     * database by username.
     */
    private String[] pagesAuthoride = { "login", "index" };

    @Override
    public void afterPhase(PhaseEvent event) {
	if (!usuarioLogado.isLogado()) {

	    boolean authorized = false;

	    FacesContext context = event.getFacesContext();

	    String view = context.getViewRoot().getViewId();

	    for (String p : pagesAuthoride) {
		if (view.contains(p)) {
		    authorized = true;
		}
	    }
	    if (!authorized) {
		NavigationHandler handler = context.getApplication()
			.getNavigationHandler();

		handler.handleNavigation(context, null,
			"login?faces-redirect=true");

		context.renderResponse();
	    }
	}
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
	return PhaseId.RESTORE_VIEW;
    }

}

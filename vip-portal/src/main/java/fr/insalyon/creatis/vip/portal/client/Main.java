/* Copyright CNRS-CREATIS
 *
 * Rafael Silva
 * rafael.silva@creatis.insa-lyon.fr
 * http://www.creatis.insa-lyon.fr/~silva
 *
 * This software is a grid-enabled data-driven workflow manager and editor.
 *
 * This software is governed by the CeCILL  license under French law and
 * abiding by the rules of distribution of free software.  You can  use,
 * modify and/ or redistribute the software under the terms of the CeCILL
 * license as circulated by CEA, CNRS and INRIA at the following URL
 * "http://www.cecill.info".
 *
 * As a counterpart to the access to the source code and  rights to copy,
 * modify and redistribute granted by the license, users are provided only
 * with a limited warranty  and the software's author,  the holder of the
 * economic rights,  and the successive licensors  have only  limited
 * liability.
 *
 * In this respect, the user's attention is drawn to the risks associated
 * with loading,  using,  modifying and/or developing or reproducing the
 * software by the user in light of its specific status of free software,
 * that may mean  that it is complicated to manipulate,  and  that  also
 * therefore means  that it is reserved for developers  and  experienced
 * professionals having in-depth computer knowledge. Users are therefore
 * encouraged to load and test the software's suitability as regards their
 * requirements in conditions enabling the security of their systems and/or
 * data to be ensured and,  more generally, to use and operate it in the
 * same conditions as regards security.
 *
 * The fact that you are presently reading this means that you have had
 * knowledge of the CeCILL license and that you accept its terms.
 */
package fr.insalyon.creatis.vip.portal.client;

import fr.insalyon.creatis.vip.portal.client.view.main.MainLeftPanel;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.widgets.MessageBox;
import fr.insalyon.creatis.vip.portal.client.bean.Configuration;
import fr.insalyon.creatis.vip.portal.client.rpc.ConfigurationService;
import fr.insalyon.creatis.vip.portal.client.rpc.ConfigurationServiceAsync;
import fr.insalyon.creatis.vip.common.client.view.Context;
import fr.insalyon.creatis.vip.portal.client.view.layout.Layout;

/**
 *
 * @author Rafael Silva
 */
public class Main implements EntryPoint {

    public void onModuleLoad() {
        ConfigurationServiceAsync service = ConfigurationService.Util.getInstance();
        final AsyncCallback<Configuration> callback = new AsyncCallback<Configuration>() {

            public void onFailure(Throwable caught) {
                MessageBox.alert("Error", "Error executing get user\n" + caught.getMessage());
            }

            public void onSuccess(Configuration result) {
                Context.getInstance().setAuthentication(result.getAuthentication());
                Context.getInstance().setQuickstartURL(result.getQuickstartURL());
                Context.getInstance().setMoteurServerHost(result.getMoteurServerHost());
                Context.getInstance().setUserHome(result.getUserHome());
                buildLayout();
            }
        };
        service.loadConfiguration(callback);
    }

    private void buildLayout() {
        Layout layout = Layout.getInstance();
        layout.setLeftPanel(new MainLeftPanel());
    }
}

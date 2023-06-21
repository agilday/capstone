import petproClient from '../api/petproClient';
import BindingClass from "../util/bindingClass";
import Header from '../components/header';
import DataStore from "../util/DataStore";

class ViewAllClientProfiles extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'getAllClientProfiles', 'redirectCreateClientProfile', 'redirectAllClientProfiles',
                'redirectCreateAppointment','redirectAllAppointments', 'redirectGetServiceMenu', 'logout'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.getAllClientProfiles);
        this.header = new Header(this.dataStore);

    }

    /**
     * Once the client is loaded, get the profile metadata.
     */
    async clientLoaded() {
        const identity = await this.client.getIdentity();
        const clientprofiles = await this.client.getAllClientProfiles();
        this.dataStore.set("clientprofiles", clientprofiles);
        this.dataStore.set("email", identity.email);

    }


    /**
     * Add the header to the page and load the petproClient.
     */
    mount() {
        document.getElementById('AllAppointments').addEventListener('click', this.redirectAllAppointments);
        document.getElementById('CreateAppointment').addEventListener('click', this.redirectCreateAppointment);
        document.getElementById('ServiceMenu').addEventListener('click', this.redirectGetServiceMenu);
        document.getElementById('AllClientProfiles').addEventListener('click', this.redirectAllClientProfiles);
        document.getElementById('CreateClientProfile').addEventListener('click', this.redirectCreateClientProfile);
        document.getElementById('logout').addEventListener('click', this.logout);
        this.client = new petproClient();
        this.clientLoaded();
    }


    getAllClientProfiles(){
            const clientprofiles = this.dataStore.get("clientprofiles");
            console.log(clientprofiles , "from getAllClientProfiles");
            if (clientprofiles == null) {
                document.getElementById("client-profiles-list").innerText = "No Client Profiles found";
            }
            document.getElementById("client-profiles-list").innerHTML = this.getHTMLForSearchResults(clientprofiles);
    }

    getHTMLForSearchResults(searchResults) {
     console.log(searchResults , "from getHTMLForSearchResults");
            if (!searchResults) {
                return '<h4>No results found</h4>';
            }
            let html = "";
            for (const res of searchResults) {
                html += `
                <tr>
                <td>
                         ${res.id}
                 </td>
                 <td>
                         ${res.name}
                  </td>
                  <td>
                         ${res.phone}
                   </td>
                    <td>
                         ${res.address}
                  </td>
                  <td>
                          ${res.notes}
                 </td>
                  <td>
                          ${res.pets}
                 </td>
                </tr>`;
            }
            return html;
        }


    redirectAllClientProfiles(){
        window.location.href = '/AllClientProfiles.html';
    }
    redirectCreateAppointment(){
        window.location.href = '/CreateAppointment.html';
    }
    redirectCreateClientProfile(){
        window.location.href = '/CreateClientProfile.html'
    }
    redirectAllAppointments(){
        window.location.href = '/AllAppointments.html';
    }
    redirectGetServiceMenu(){
        window.location.href = '/ServiceMenu.html';
    }
    async logout(){
        await this.client.logout();
        if(!this.client.isLoggedIn()){
            window.location.href ='/LandingPage.html';
        }

    }

}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewAllClientProfiles = new ViewAllClientProfiles();
    viewAllClientProfiles.mount();
};

window.addEventListener('DOMContentLoaded', main);
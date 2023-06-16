import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the DannaAPIService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class petproClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getClientProfile', 'getAllClientProfiles', 'getAllAppointments','createAppointment',
                                'createService','updateClientProfile','updateService','createClientProfile','getServiceMenu', 'isLoggedIn'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    async isLoggedIn(){
        return this.authenticator.isUserLoggedIn();
    }
    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }
            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Gets the profile for the given ID.
     * @param id Unique identifier for a profile
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The profile's metadata.
     */
    async getClientProfile(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can view a client profile.");
            const response = await this.axiosClient.get(`profiles/${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

   /**
    * @param  errorCallback (Optional) a function to execute on a failed call
    * @returns all the appointments
    */
    async getAllAppointments(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get all appointments.");
            const response = await this.axiosClient.get(`appointments/all/`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
    
    /**
    * 
    * @param  errorCallback (Optional) a function to execute on a failed call
    * @returns all services
    */
    async getServiceMenu(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get service menu.");
            const response = await this.axiosClient.get(`servicemenu/`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * 
     * @param  id Unique identifier for profile
     * @param  name name
     * @param  phone phone
     * @param  address address
     * @param  notes notes
     * @param  pets pets
     * @param  errorCallback 
     * @returns profile metadata
     */
    async createClientProfile(name, phone, address, notes, pets, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create a profile.");
            const response = await this.axiosClient.post(`clientprofiles/create`, {
                name: name,
                phone: phone,
                address: address,
                notes: notes,
                pets: pets
            }, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
            console.log(response.data);
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
         *
         * @param  id Unique identifier for profile
         * @param  name name
         * @param  phone phone
         * @param  address address
         * @param  notes notes
         * @param  pets pets
         * @param  errorCallback
         * @returns profile metadata
         */

    async updateClientProfile(id, name, phone, address, notes, pets, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can update a profile.");
            const response = await this.axiosClient.put(`profiles/${id}`, {
                name: name,
                phone: phone,
                address: address,
                notes: notes,
                pets: pets
            }, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
    /**
     * 
     * @param  client owner of pet
     * @param  dateTime date of the appt
     * @param  pet pet receiving service
     * @param  service service being performed
     * @param  errorCallback 
     * @returns the event
     */
    async createAppointment(client, dateTime, pet, service, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create an event.");
            const response = await this.axiosClient.post(`events/create`, {
                client: client,
                dateTime: dateTime,
                pet: pet,
                service: service
            }, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'

                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     *
     * @param  errorCallback 
     * @returns all client profiles
     */
    async getAllClientProfiles(errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can update events.");
            const response = await this.axiosClient.put(`clientprofiles/`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
    /**
     * 
     * @param {*} service the service to add to the menu
     * @param {*} errorCallback 
     * @returns 
     */
    async addServiceToServiceMenu(service, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can add services to the menu.");
            const response = await this.axiosClient.put(`profiles/addServiceToServiceMenu`, {
                service: service
            }, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'application/json'
                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

//     /**
//     *
//     * @param {*} id Unique identifyer of the profile
//     * @param {*} profileId Unique identifyer of the profile to remove from the following list
//     * @param {*} errorCallback
//     * @returns
//     */
//    async removeFromFollowing(profileId, errorCallback) {
//        try {
//            const token = await this.getTokenOrThrow("Only authenticated users can remove a profile.");
//            const response = await this.axiosClient.put(`profiles/removeFollowing`, {
//                profileId: profileId
//            }, {
//                headers: {
//                    Authorization: `Bearer ${token}`,
//                    'Content-Type': 'application/json'
//                }
//            });
//            return response.data;
//        } catch (error) {
//            this.handleError(error, errorCallback)
//        }
//    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}

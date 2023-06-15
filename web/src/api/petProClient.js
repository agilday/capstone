import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the PetProSchedulerService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class PetProClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getAllAppointments', 'createAppointment',
         'createClientProfile', 'getAllClientProfiles', 'getClientProfile', 'updateClientProfile', 'getServiceMenu', 'updateService'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
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
     * Gets all appointments.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The appointments list.
     */
    async getAllAppointments(errorCallback) {
        try {
            const response = await this.axiosClient.get(`appointments`);
            return response.data.appointmentsList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Get all client profiles.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of client profiles.
     */
    async getAllClientProfiles(errorCallback) {
        try {
            const response = await this.axiosClient.get(`clientProfiles`);
            return response.data.profilesList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Get all client profiles.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of client profiles.
     */
     async getClientProfile(id, errorCallback) {
         try {
             const response = await this.axiosClient.get(`clientProfiles/${id}`);
             return response.data.profilesList;
         } catch (error) {
             this.handleError(error, errorCallback)
         }
    }

    /**
     * Get service menu.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of services.
     */
     async getServiceMenu(errorCallback) {
         try {
             const response = await this.axiosClient.get(`serviceMenu`);
             return response.data.servicesList;
         } catch (error) {
             this.handleError(error, errorCallback)
         }
     }

    /**
     * Create a new appointment.
     * @param client The client being scheduled.
     * @param dateTime the date and time of the appointment.
     * @param pet the pet being groomed.
     * @param service the service being performed.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The appointment that has been created.
     */
    async createAppointment(client, dateTime, pet, service, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create appointments");
            const response = await this.axiosClient.post(`appointments`, {
                client: client,
                dateTime: dateTime,
                pet: pet,
                service: service
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.appointment;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Create a new client profile.
     * @param name The client name.
     * @param phone the client's phone number.
     * @param address the client's address.
     * @param notes the client notes.
     * @param pets the client's pets.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The appointment that has been created.
     */
     async createAppointment(name, phone, address, notes, pets, errorCallback) {
        try {
           const token = await this.getTokenOrThrow("Only authenticated users can create appointments");
           const response = await this.axiosClient.post(`appointments`, {
               name: name,
               phone: phone,
               address: address,
               notes: notes,
               pets: pets
            }, {
             headers: {
                 Authorization: `Bearer ${token}`
                    }
            });
              return response.data.appointment;
        } catch (error) {
              this.handleError(error, errorCallback)
        }
     }

    /**
    * updates a client's profile
    * @param  id Unique identifier for profile
    * @param  name the client's name
    * @param  phone the client's phone number
    * @param  address the client's address
    * @param  notes client notes
    * @param  pets the client's pets
    * @param  errorCallback
    * @returns clientProfile metadata
    */

    async updateClientProfile(id, name, phone, address, notes, pets, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can update a profile.");
            const response = await this.axiosClient.put(`clientprofiles/${id}`, {
                    id: id,
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
    * update service
    * @param title the title of the service
    * @param description the description of the service
    * @param errorCallback
    */
    async updateService(title, description, errorCallback) {
        try {
           const token = await this.getTokenOrThrow("Only authenticated users can update a service.");
           const response = await this.axiosClient.put(`serviceMenu/title`, {
                   title: title,
                   description: description
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
     * Search for a song.
     * @param criteria A string containing search criteria to pass to the API.
     * @returns The playlists that match the search criteria.
     */
    async search(criteria, errorCallback) {
        try {
            const queryParams = new URLSearchParams({ q: criteria })
            const queryString = queryParams.toString();

            const response = await this.axiosClient.get(`playlists/search?${queryString}`);

            return response.data.playlists;
        } catch (error) {
            this.handleError(error, errorCallback)
        }

    }

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

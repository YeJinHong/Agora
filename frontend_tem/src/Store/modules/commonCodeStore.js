import {router} from "../../router";
import {getCommonCodes} from "../../api/CommonCode";

const commonCodeStore = {
    namespaced: true,
    state: {
        categories: null,
    },
    getters: {
        checkCategories: function (state) {
            return state.categories;
        }
    },
    mutations: {
        SET_CATEGORIES: (state, categories) => {
            state.categories = categories;
        }
    },
    actions: {
        getCategories({commit}) {
            getCommonCodes(({data}) => {
                    if (data.message === "Success") {
                        let categories = data["categories"];
                        commit("SET_CATEGORIES", categories);
                        sessionStorage.setItem("categories", categories);
                    }
                },
                (error) => {
                    alert("error");
                }
            );
        },
    }
};

export default commonCodeStore;

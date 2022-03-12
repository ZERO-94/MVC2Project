/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhtvk.utils;

/**
 *
 * @author kiman
 */
public class MVC2ProjectConstants {
    public class DispatchFeatures {
        public static final String LOGIN_PAGE = "";
        public static final String LOGIN_CONTROLLER = "loginAction";
        public static final String CREATE_ACCOUNT_CONTROLLER = "createAccountAction";
        public static final String INVALID_PAGE="invalidPage";
        public static final String ERROR_PAGE="errorPage";
        public static final String CREATE_ACCOUNT_HTML_PAGE = "createAccountPage";
        public static final String CREATE_ACCOUNT_JSP_PAGE = "createAccountErrorPage";
        public static final String LOGIN_JSP_PAGE = "loginErrorPage";
        public static final String GET_ALL_PRODUCTS_CONTROLLER = "getAllProductsAction";
        public static final String SHOPPING_SEARCH_CONTROLLER = "searchShoppingAction";
        public static final String NOT_FOUND_PAGE = "notFoundPage";
    }
    
    public class LoginFeatures {
        public static final String SEARCH_PAGE = "searchPage";
        public static final String SEARCH_LASTNAME_CONTROLLER = "searchLastnameAction";
        public static final String DELETE_CONTROLLER = "deleteAccountAction";
        public static final String UPDATE_CONTROLLER = "updateAccountAction";
        public static final String LOGOUT_CONTROLLER = "logoutAction";
        public static final String UPDATE_ACCOUNT_PAGE = "updateAccountPage";
        public static final String UPDATE_ROLE_CONTROLLER = "updateRoleAction";
        public static final String UPDATE_GENERAL_INFORMATION_CONTROLLER = "updateGeneralInformationAction";
        public static final String UPDATE_PASSWORD_CONTROLLER = "updatePasswordAction";
    }
    
    public class CartFeatures {
        public static final String SHOPPING_PAGE = "shoppingPage";
        public static final String SHOPPING_SEARCH_PAGE = "searchShoppingPage";
        public static final String SHOW_CART_PAGE="cartPage";
        public static final String ADD_ITEM_TO_CART_CONTROLLER="addItemToCartAction";
        public static final String DELETE_ITEM_FROM_CART_CONTROLLER="deleteItemFromCartAction";
    }
}

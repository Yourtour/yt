/**
 * Created by john on 15-8-24.
 */
Ext.define('yt_manager_app.view.route.RouteWindow', {
    extend: 'yt_manager_app.view.widget.GeneralFormWindow',
    xtype: 'routeWindow',
    reference: 'routePopupWindow',

    config: {
        base: {
            items: null,
            hidden: false
        },
        detail: {
            items: null,
            hidden: false
        },
        extend: {
            items: null,
            hidden: false
        },
        operate: {
            hidden: false
        }
    }
});
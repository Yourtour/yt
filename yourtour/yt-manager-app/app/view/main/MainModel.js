/**
 * This class is the view model for the Main view of the application.
 */
Ext.define('yt_manager_app.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.main',

    data: {
        name: 'yt_manager_app',

        logoHTML: 'YT数据维护平台',

        username: 'Nan',

        toggle: {
            state: true,
            availWidth: 1024,
            availHeight: 900,
            left: {
                minLength: 46,
                maxLength: 240
            },
            headerHeight: 70,
            currentViewName: 'Welcome ...'
        },

        footer: {
            company: '上海游徒网络信息服务有限公司',
            copyright: '&copy;2014-2015'
        },

        loremIpsum: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'
    }

    //TODO - add data, formulas and/or methods to support your view
});

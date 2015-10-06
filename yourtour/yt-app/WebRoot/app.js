/*
    This file is generated and updated by Sencha Cmd. You can edit this file as
    needed for your application, but these edits will have to be merged by
    Sencha Cmd when it performs code generation tasks such as generating new
    models, controllers or views and when running "sencha app upgrade".

    Ideally changes to this file would be limited and most work would be done
    in other places (such as Controllers). If Sencha Cmd cannot merge your
    changes and its generated code, it will produce a "merge conflict" that you
    will need to resolve manually.
*/

Ext.application({
    name: 'YourTour',

    requires: [
        'Ext.MessageBox', 'Ext.form.Hidden','YourTour.util.Context','YourTour.view.home.BestItemView'
    ],

    views: [
        'MainView','Launch',
        'home.HomeMainView', 'home.BestListView', 'home.AlongListView', 'home.AlongDetailView', 'home.TalentListView', 'SearchMain',
        'route.RouteMainView','route.RouteSettingView','common.PlaceView',
        'line.LineRecommendView','line.LineIntroductionView',
        'route.schedule.SchedulePlanListView', 'route.schedule.ScheduleListView','route.schedule.SceneScheduleView','route.schedule.HotelScheduleView','route.schedule.FoodScheduleView',
        'user.LoginMainView','user.UserListView',
        'resource.SelectionListView','resource.SceneResourceDetailView','resource.SceneResourcePlanView','route.schedule.ScheduleReferenceListView',
        'personal.PersonalMainView'
    ],
    
    controllers: [
        'Launch', 'MainCtrl', 
        'home.HomeMainCtrl', 'home.BestMainCtrl','home.TalentMainCtrl','home.AlongMainCtrl',
        'route.RouteMainCtrl','route.RouteSettingCtrl','route.ScheduleListCtrl', 'route.SchedulePlanListCtrl', 'line.LineRecommendCtrl','line.LineIntroductionCtrl','common.PlaceCtrl','user.Main',
        'resource.ResourceSelectionCtrl','route.ScheduleReferenceCtrl','resource.ResourceCtrl','resource.ResourcePlanCtrl','route.ScheduleDetailCtrl',
        'user.UserListCtrl'
    ],
    
    models:[
        'RouteModel','ScheduleModel','LineModel', 'UserModel','OptionModel', 'PlaceModel','LiveModel','ChatModel','AlongModel','TalentModel','PlaceCarouselModel','CommentModel' 
    ],
    
    stores:[
        'RouteStore','LineStore','ResourceStore','ResourceSelectionStore', 'UserStore', 'PlaceStore', 'BestListStore','TalentListStore', 'AlongListStore','CommentStore'
    ],
        
    icon: {
        '57': 'resources/icons/Icon.png',
        '72': 'resources/icons/Icon~ipad.png',
        '114': 'resources/icons/Icon@2x.png',
        '144': 'resources/icons/Icon~ipad@2x.png'
    },
    
    isIconPrecomposed: true,
    
    startupImage: {
        '320x460': 'resources/startup/launch.jpg',
        '640x920': 'resources/startup/launch.png',
        '768x1004': 'resources/startup/launch.png',
        '748x1024': 'resources/startup/launch.png',
        '1536x2008': 'resources/startup/launch.png',
        '1496x2048': 'resources/startup/launch.png'
    },

    launch: function() {
        
    },

    onUpdated: function() {
        Ext.Msg.confirm(
            "Application Update",
            "This application has just successfully been updated to the latest version. Reload now?",
            function(buttonId) {
                if (buttonId === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});

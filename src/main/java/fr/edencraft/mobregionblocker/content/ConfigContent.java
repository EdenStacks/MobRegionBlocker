package fr.edencraft.mobregionblocker.content;

public class ConfigContent {

    public static String CONTENT;

    static {
        CONTENT = """
                # ==================================================================================================#
                #\t                                                                                                #
                #\t     __  ___      __    ____             _             ____  __           __           \s        #
                #\t    /  |/  /___  / /_  / __ \\___  ____ _(_)___  ____  / __ )/ /___  _____/ /_____  _____        #
                #\t   / /|_/ / __ \\/ __ \\/ /_/ / _ \\/ __ `/ / __ \\/ __ \\/ __  / / __ \\/ ___/ //_/ _ \\/ ___/        #
                #\t  / /  / / /_/ / /_/ / _, _/  __/ /_/ / / /_/ / / / / /_/ / / /_/ / /__/ ,< /  __/ /   \s        #
                #\t /_/  /_/\\____/_.___/_/ |_|\\___/\\__, /_/\\____/_/ /_/_____/_/\\____/\\___/_/|_|\\___/_/    \s        #
                #\t                               /____/                                                  \s        #
                #\t                                                                                                #
                # ==================================================================================================#
                
                # This plugin has been created for https://www.edencraft.fr by Hope51 and NayeOne.
                # Contact NayeOne#4734 on Discord if you need help.
                
                # DO NOT TOUCH THIS IF YOU DON'T KNOW WHAT YOU ARE DOING !
                config-version: 1.0
                
                # All mythic mob specified here will be remove if they leave their spawn region.
                only-spawn-region:
                  - mob1
                  - mob2
                """;
    }

}

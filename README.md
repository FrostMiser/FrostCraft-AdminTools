FrostCraft AdminTools
=================
This is a Minecraft Bukkit plugin which provides a set of administrative tools designed for use on FrostCraft.com servers.

Commands
=================
* (Not Implemented) /auth - Mechanism for providing an authorization code to use the plugin
* /kick [player] - Kicks a player but does not add a warning to their account
* /kickwarn [Player] - Kicks a player and adds a warning to their account
* /ban [Player] - Bans a player from the server
* /tempban [Player] [Days] - Issues a temporary ban to a player for [Days] days
* /mute [Player] - Mutes a player.
* /unmute [Player] - Unmutes a player
* /vanish - Hides your name from tab and makes you invisible to other players.
* /vanishfrom [Player] - Hides your name from tab and makes you invisible only to a specific player.
* /seeinv [Player] - View a players inventory
* /seeinvender [Player] - View a players ender inventory
* /tpl [Player] - Teleport to a player
* /gm [Optional Player] - Change gamemode of self or player (if specified).
* /time [Night|Day] - Set time to night or day
* /togglephysics - Turn server physics on or off
* /addpoint [Name] - Adds a waypoint point that you can teleport back to later
* /listpoints - Lists all of your waypoints
* /point [Name] - Teleports you to a saved waypoint

Permissions
=================
* fcadmin.admin Permission to use the /fcadmin command. Players must have this permission to use any of the fcadmin subcommands.
* (Not Implemented) fcadmin.[subcommand] Permission to use /fcadmin [subcommand]. You must set both the main /fcadmin permission and /fcadmin [subcommand] permission for a player to have access to a specific subcommand (unless they are OP).















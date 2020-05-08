# Advanced-SB
 Per-world, permission based, custom scoreboards!

# Setup

In order to get the plugin running on your server, you need to first install it using [this link](https://github.com/saltyfishhy/Advanced-SB/raw/master/out/artifacts/Advanced_SB_jar/Advanced%20SB.jar). Then, place the plugin in your /plugins folder for your server. Restart the server and it should be up and running!

# Configuration

The config has two properties: 

- refreshRate - The amount of time (in seconds) before the next scoreboard refresh happens. Can't be equal to or lower than 1 or the plugin will break.
- requirePermission - Whether or not a player must have a permission to see a scoreboard. The permission will be "advancedsb.show.(scoreboardID)

To edit these properties, locate the config.yml file within the AdvancedSB folder.

# Making Scoreboards

All scoreboards are contained within a file called scoreboards.yml, located in your AdvancedSB folder. The file generates with 1 scoreboard already, which you can edit to your liking. To create more scoreboards, copy the text for the scoreboard it gives you and paste it under. Example: 

```
scoreboards:
  '1':
    hidden: false
    worlds:
    - world
    title: '&6Test Scoreboard'
    lines:
      line1: 'Test'
      line2:
      line3:
      line4:
      line5:
      line6:
      line7:
      line8:
      line9:
      line10:
      line11:
      line12:
      line13:
      line14:
      line15:
  '2':
    hidden: false
    worlds:
    - world2
    title: '&6Test Scoreboard 2'
    lines:
      line1: 'Test'
      line2:
      line3:
      line4:
      line5:
      line6:
      line7:
      line8:
      line9:
      line10:
      line11:
      line12:
      line13:
      line14:
      line15:
```

Repeat this as many times as you like to create as many scoreboards as you want. 

In order to enable a scoreboard for all worlds, type "- all" under worlds:. If you want a scoreboard to be visible for multiple worlds, format it like a list, and do:

```worlds:
 - world1
 - world2
 ```
 
 # Commands
 
 Currently, the plugin has one command:
 
 - /scoreboard <show | hide> (scoreboard ID)
 
 This command will alter whether or not the scoreboard is hidden, thus changing the "hidden" property of a scoreboard. The scoreboard ID is the number that the scoreboard is contained within in the scoreboards.yml file.
 
 # Bugs
 
 If you have any issues with the plugin, be sure to join [my discord server](https://discord.gg/CAjazrs) to get support. 

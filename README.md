# BorderAPI 1.13.x

Sends a red screen for a certain time for player.  
`BorderAPI.sendRedScreen(Player player, int time);`

Change the radius per player.  
`BorderAPI.setBorder(Player player, double radius);`  

Change the global WorldBorder radius.  
`BorderAPI.setBorder(double radius);` 

Sets the location,radius of the players WorldBorder.  
`BorderAPI.setBorder(Player player, double radius, Location location);`

Change the radius of the players WorldBorder in a certain time.  
`BorderAPI.setBorder(Player player, double radius, int seconds);`  

Changes the radius of the WorldBorder in a certain time.  
`BorderAPI.setBorder(double radius, int seconds);`

With this method you are able to set your own modified WorldBorder an the certain action 
`BorderAPI.setBorder(Player player, WorldBorder border, WorldBorderAction action);`

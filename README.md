WorldBorderAPI 1.12.x
==================
Sends a red screen for a certain time for player.  
`WorldBorderAPI.sendRedScreen(Player player, int time);`

Change the radius per player.  
`WorldBorderAPI.setBorder(Player player, double radius);`  
Change the global WorldBorder radius.  
`WorldBorderAPI.setBorder(double radius);` 

Sets the location,radius of the players WorldBorder.  
`WorldBorderAPI.setBorder(Player player, double radius, Location location);`

Change the radius of the players WorldBorder in a certain time.  
`WorldBorderAPI.setBorder(Player player, double radius, int seconds);`  
Changes the radius of the WorldBorder in a certain time.  
`WorldBorderAPI.setBorder(double radius, int seconds);`

With this method you are able to set your own modified WorldBorder an the certain action 
`WorldBorderAPI.setBorder(Player player, WorldBorder border,
			WorldBorderAction action);`

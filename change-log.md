v1.4.4.0
========

The next version of MapTool Nerps! The version bump is due to a completely new packaging scheme using native installer packages and getting rid of the Launcher application. This fork also now uses it's own app folder (.maptool-nerps) so you can run it side by side without interference of the main RPTools branch/distribution! JVM preferences are now stored…


Enhancements
------------
	* #8  - New packaging mechanism! OS specific "installs" are now being generated using Oracles native javapackager tool. It packages the JAR, native executable, and JRE in the following packages: .exe (Windows), .dmg & .pkg (MacOS), & .deb (Linux) as well as unified JAR that can be run manually.
	* #26 - MapTool now checks and alerts user when a new version is available as a GitHub release and allow you to download it! You can "skip" a version to stop alerting until the next release comes out or cancel all auto update checks. It will download the release based on your OS (.exe, .pkg, or .deb)
	* #9  - New macro function added to change token ownership to 'Owned by All', setOwnedByAll(boolean [,tokenID]) returns boolean
	* #22 - Darkvision changed to Darkvision: circle r60 (removed distance=62.5)
	* #25 - Cone lights now accept 'offset=x' as an option just like vision
	* #33 - Java stack traces are sent automatically to Sentry.io for aggregation and notification. No private info is gathered or sent. This lets me know if an unreported bug shows up and how critical may be so I can get it fixed quicker and with minimal info needed from users.
	
	  
Bug Fixes
---------
	* #5  - Adding new state causes java.lang.ArrayIndexOutOfBoundsException no more!
	* #6  - Fixed various Typos
	* #15 - Lighting wasn't immediately forced to connected clients and should be fixed now.
	* #18 - Exporting Campaigns back to 1.4.0.1 was failing due to new objects added. You can now export any campaign back to 1.4.0.1 but as always, this is permanent in that it will strip out new objects as in TokenVBl, new lighting options, etc but macros will not be touched and may fail if they contain new macro functions not available in 1.4.0.1.
	* #19 - Default save location for tokens are now remembered!
	* #20 - Added missing documentation for lights/vision, e.g. 'scale'
	* #21 - Lights are not updating properly based on ownership looked like it was tied to other 'light' bugs and should now be fixed.
	* #23 - Fixed sendToBack & bringToFront macros broke states and bar changes in the macro. This was an OLD one going back to 1.3b63! You can now safely use these functions in your macro now!
	* #30 - Players see NPC movement when there are no lights no more! This was another old bug going back to 1.3b-something and only showed itself if you had NO lights (including personal lights, aka darkvision).
# TeamspeakAPI

TeamspeakAPI is an API to connect to Teamsepak Servers as an Admin.

# Examples

##Simple Connection:
```java
public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				TeamspeakAPI tsAPI = new TeamspeakAPI("127.0.0.1", true, new TSAdminAccount("serveradmin", "QyoluVCo"), true);
				try {
					tsAPI.start();
					
					tsAPI.sendMessage("hey");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
```

##Explanation


######Classes
Currently there are three classes:

| Class                          | Explanation                                                           |
| ------------------------------ | --------------------------------------------------------------------- |
| TeamspeakAPI                   | the main api class which connects to the server                       |
| TSAdminAccount                 | the account data class saves the login data of the server             |
| TSCommand                      | the command class contains the commands of the server                 |


##License

Copyright 2015 Marcel Franzen

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

/**
 * src/ios: OpenSchemeUrl.h
**/

#import <Cordova/CDVPlugin.h>

@interface OpenSchemeUrl : CDVPlugin
    - (void) open: (CDVInvokedUrlCommand*) command;
    - (void) isInstalled: (CDVInvokedUrlCommand*) command;
@end;

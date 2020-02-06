//
//  RNHelpCrunch.m
//  ScoutingApp
//
//  Created by Aleksandr Mansurov on 12/13/19.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "RNHelpCrunch.h"
#import <HelpCrunchSDK/HelpCrunch.h>

@implementation RNHelpCrunch

RCT_EXPORT_MODULE()

// Available as NativeModules.RNHelpCrunch.show
RCT_EXPORT_METHOD(show :(RCTPromiseResolveBlock)resolve :(RCTPromiseRejectBlock)reject) {
  NSLog(@"show");

  dispatch_async(dispatch_get_main_queue(), ^{
    UIViewController *rootViewController = [UIApplication sharedApplication].delegate.window.rootViewController;
    [HelpCrunch showFromController:rootViewController completion:^(NSError * _Nullable error) {
      // If you need to do something on completion of SDK view controller presenting
      resolve([NSNull null]);
    }];
  });
};


// Available as NativeModules.RNHelpCrunch.updateUser
RCT_EXPORT_METHOD(updateUser:(NSDictionary*)options :(RCTPromiseResolveBlock)resolve :(RCTPromiseRejectBlock)reject) {
  NSLog(@"updateUser with %@", options);
  
  HCSUser *user = [HCSUser new];

  NSString* userId         = options[@"userId"];
  NSString* userEmail      = options[@"userEmail"];
  NSDictionary* customData = options[@"customData"];

  if ([userId isKindOfClass:[NSNumber class]]) {
    userId = [(NSNumber *)userId stringValue];
  }

  if (userId.length > 0) {
    user.userId = userId;
    if (userEmail.length > 0) {
      user.email = userEmail;
      user.name = userEmail;
    }
    user.customData = customData;

    [HelpCrunch updateUser:user completion:^(NSError * _Nullable error) {
      // We're ready to log user in
      resolve(userId);
    }];
  } else {
    NSLog(@"[HelpCrunch] ERROR - No user registered. You must supply userId");
    reject(@"", @"No user registered. You must supply userId", nil);
  }
};

// Available as NativeModules.RNHelpCrunch.logout
RCT_EXPORT_METHOD(logout :(RCTPromiseResolveBlock)resolve :(RCTPromiseRejectBlock)reject) {
  NSLog(@"logout");

  dispatch_async(dispatch_get_main_queue(), ^{
    [HelpCrunch logoutWithCompletion:^(NSError * _Nullable error) {
      // User successfully logged out
      resolve([NSNull null]);
    }];
  });
};

@end

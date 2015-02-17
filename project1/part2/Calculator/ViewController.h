//
//  ViewController.h
//  Calculator
//
//  Created by Matthew Wichmann on 1/18/15.
//  Copyright (c) 2015 mwichmann. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController

@property (weak, nonatomic) IBOutlet UILabel *display;
@property (weak, nonatomic) IBOutlet UILabel *history;

@end

@protocol ControllerDelegate <NSObject>
@property (nonatomic) id delegateController;
@end


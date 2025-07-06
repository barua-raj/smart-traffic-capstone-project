-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 11, 2022 at 12:49 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `finalproject_traffic_info`
--

-- --------------------------------------------------------

--
-- Table structure for table `add_my_driver`
--

CREATE TABLE `add_my_driver` (
  `id` int(11) NOT NULL,
  `d_name` varchar(500) DEFAULT NULL,
  `Driving_license_no` varchar(500) DEFAULT NULL,
  `Vehicle_reg_no` varchar(500) DEFAULT NULL,
  `user_name` varchar(500) DEFAULT NULL,
  `phone` varchar(500) DEFAULT NULL,
  `location` varchar(500) DEFAULT NULL,
  `status` varchar(22) NOT NULL,
  `image` varchar(222) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `add_my_driver`
--

INSERT INTO `add_my_driver` (`id`, `d_name`, `Driving_license_no`, `Vehicle_reg_no`, `user_name`, `phone`, `location`, `status`, `image`) VALUES
(39, 'Muhammad Ali Haider', 'DK0121988M00008', 'CHATTA METRO-GA-79-0120', 'ashik', '2467884', 'Chattogram', 'Accepted', 'ali.jpeg'),
(40, 'Joyraz Barua', 'CG7906987M03117', 'CHATTA METRO-BHA-78-0029', 'chinmoy', '8686 ', 'jvjg', 'Accepted', 'joyraz.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(22) NOT NULL,
  `email` varchar(222) NOT NULL DEFAULT 'admin@gmail.com',
  `password` varchar(222) NOT NULL DEFAULT 'admin',
  `fname` varchar(222) NOT NULL,
  `lname` varchar(222) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `email`, `password`, `fname`, `lname`) VALUES
(1, 'admin@gmail.com', 'admin', 'BRTC', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `bkash_account`
--

CREATE TABLE `bkash_account` (
  `id` int(222) NOT NULL,
  `phone` varchar(111) NOT NULL,
  `pin` varchar(222) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bkash_account`
--

INSERT INTO `bkash_account` (`id`, `phone`, `pin`) VALUES
(2, '01784200859', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `cases`
--

CREATE TABLE `cases` (
  `id` int(11) NOT NULL,
  `Applicant_name` varchar(500) DEFAULT NULL,
  `Applicant_type` varchar(500) DEFAULT NULL,
  `License_no` varchar(500) DEFAULT NULL,
  `crime` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `imprisonment` varchar(500) DEFAULT NULL,
  `fine` varchar(500) DEFAULT NULL,
  `fine_type` varchar(500) DEFAULT NULL,
  `Vehicle_type` varchar(500) DEFAULT NULL,
  `Vehicle_number` varchar(500) DEFAULT NULL,
  `Type_of_fine` varchar(500) DEFAULT NULL,
  `NID_No` varchar(500) DEFAULT NULL,
  `Police_id` varchar(500) DEFAULT NULL,
  `feedback` varchar(2000) DEFAULT NULL,
  `Status` varchar(500) DEFAULT NULL,
  `address` varchar(222) NOT NULL,
  `case_date` varchar(111) NOT NULL,
  `case_time` varchar(111) NOT NULL,
  `case_location` varchar(222) NOT NULL,
  `seized_document` varchar(222) NOT NULL,
  `appearance_last_date` varchar(222) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cases`
--

INSERT INTO `cases` (`id`, `Applicant_name`, `Applicant_type`, `License_no`, `crime`, `imprisonment`, `fine`, `fine_type`, `Vehicle_type`, `Vehicle_number`, `Type_of_fine`, `NID_No`, `Police_id`, `feedback`, `Status`, `address`, `case_date`, `case_time`, `case_location`, `seized_document`, `appearance_last_date`) VALUES
(69, 'Joyraz Barua', 'Driver', 'CG7906987M03117', 'গাড়ি থামিয়ে প্রতিবন্ধকতা বা যানজট সৃষ্টি করা।', 'None', '1000', 'First Timer', 'Motorcar', 'CHATTA METRO-GA-79-0120', 'First Timer', '8652259784', '24', '', 'Paid', 'Chattogram', '2022-07-18', '4.00 pm', 'Muradpue, Chattogram', '--Select Seized Document--', '2022-08-20'),
(70, 'Ashik Siddiquee', 'Owner', ' ', 'জরাজীর্ণ, বিবর্ণ বা দূষণকারী যানবাহন।', '15 Days', '10000', 'First Timer', 'Motorcar', 'CHATTA METRO-GA-79-0120', 'First Timer', '9012748517', '24', '', 'Paid', 'Chattogram', '2022-07-18', '4.00 pm', 'Muradpur, Chattogram', '--Select Seized Document--', '2022-08-20'),
(71, 'Chinmoy Khastagir', 'Both', 'CG0121788L00004', 'হেলমেট না পরার জন্য।', 'None', '1000', 'First Timer', 'Motorcycle', 'CHATTA METRO-DHA-81-0882', 'First Timer', '3252163104', '24', '', 'Paid', 'Chattogram', '2022-07-18', '6.00pm', 'Sadarghat, Chattogram', '--Select Seized Document--', '2022-08-08'),
(72, 'Chinmoy Khastagir', 'Both', 'CG0121788L00004', 'অত্যধিক মাত্রার পরিবেশ দূষণকারী ধোঁয়া বা অন্য কোনো ধরনের নির্গমনের ব্যবহার।', '15 Days', '2500', 'First Timer', 'Motorcycle', 'CHATTA METRO-DHA-81-0882', 'First Timer', '3252163104', '24', '', 'Pending', 'Chattogram', '2022-07-18', 'Sadarghat, Chattogram', '', 'Fitness Certificate', '2022-08-08'),
(73, 'Joyraz Barua', 'Driver', 'CG7906987M03117', 'গাড়ি চালানোর সময় ফোন বা অনুরূপ সরঞ্জাম ব্যবহার করুন। চালক সিটবেল্ট পড়েননি।', '15 Days', '1000', 'First Timer', 'Motorcar', '', 'First Timer', '8652259784', '24', '', 'Pending', 'Chattogram', '2022-07-19', '', '', '--Select Seized Document--', '2022-08-11'),
(76, 'Chinmoy Khastagir', 'Both', 'CG0121788L00004', 'অনুমোদিত ওজন থেকে অতিরিক্ত ওজন ব্যবহার।', '15 Days', '10000', 'First Timer', 'Motorcycle', 'CHATTA METRO-DHA-81-0882', 'First Timer', '3252163104', '27182927', '', 'Pending', 'Chattogram', '2022-07-23', '8:05pm', 'kazirdeurie ', 'License', '2022-08-23'),
(77, 'Chinmoy Khastagir', 'Both', 'CG0121788L00004', 'অনুমোদিত ওজন থেকে অতিরিক্ত ওজন ব্যবহার।', '1 Month', '100000', 'Second Timer', 'Motorcycle', 'CHATTA METRO-DHA-81-0882', 'Second Timer', '3252163104', '32', '', 'Pending', 'Chattogram', '2022-07-23', '9.59 pm', 'Mehedibag', 'Certificate of Registration', '2022-10-06'),
(78, 'Joyraz Barua', 'Driver', 'CG7906987M03117', 'হেলমেট না পরার জন্য।', 'None', '1000', 'First Timer', 'Motorcar', 'CHATTA METRO-BHA-78-0029', 'First Timer', '8652259784', '27182927', '', 'Pending', 'Chattogram', '2022-07-24', '4:20 pm', 'Cinema Palace, Chattogram', '--Select Seized Document--', '2022-08-24'),
(79, 'Joyraz Barua', 'Driver', 'CG7906987M03117', 'গাড়ি চালানোর সময় ফোন বা অনুরূপ সরঞ্জাম ব্যবহার করুন। চালক সিটবেল্ট পড়েননি।', '15 Days', '1000', 'First Timer', 'Motorcar', 'CHATTA METRO-BHA-78-0029', 'First Timer', '8652259784', '27182927', '', 'Paid', 'Chattogram', '2022-07-26', '3:16', '2 no. gate', 'None', '07/25/22'),
(80, 'Chinmoy Khastagir', 'Both', 'CG0121788L00004', 'বিভিন্ন শ্রেণীর লাইসেন্সের ব্যবহার পেশাগত লাইসেন্স ছাড়া পাবলিক ট্রান্সপোর্ট চালান।', '1 Month', '25000', 'Second Timer', 'Motorcar', 'CHATTA METRO-DHA-81-0882', 'Second Timer', '3252163104', '24', '', 'Pending', 'Chattogram', '2022-07-26', '15:53', 'ivib', 'None', '08/28/22'),
(81, 'Chinmoy Khastagir', 'Both', 'CG0121788L00004', 'বিভিন্ন শ্রেণীর লাইসেন্সের ব্যবহার পেশাগত লাইসেন্স ছাড়া পাবলিক ট্রান্সপোর্ট চালান।', '3 Months', '25000', 'Second Timer', 'SUV', 'CHATTA METRO-DHA-81-0882', 'Second Timer', '3252163104', '24', '', 'Pending', 'Chattogram', '2022-07-26', '14:55', 'jvkb', 'None', '07/31/22'),
(82, 'Joyraz Barua', 'Driver', 'CG7906987M03117', 'বিপরীত দিকে গাড়ি চালানোর জন্য', 'None', '1000', 'First Timer', 'Motorcar', 'CHATTA METRO-BHA-78-0029', 'First Timer', '8652259784', '27182927', '', 'Paid', 'Chattogram', '2022-08-02', '18:16', 'Kazir Dewri, Biman Office', 'None', '08/13/22'),
(83, 'Muhammad Ali Haider', 'Driver', 'DK0121988M00008', 'বিভিন্ন শ্রেণীর লাইসেন্সের ব্যবহার পেশাগত লাইসেন্স ছাড়া পাবলিক ট্রান্সপোর্ট চালান।', '15 Days', '25000', 'Second Timer', 'Microbus', 'CHATTA METRO-DHA-81-0882', 'Second Timer', '5255165104', '27182927', '', 'Pending', 'Chattogram', '2022-08-02', '21:1', 'fhjk', 'None', '08/28/22'),
(84, 'Chinmoy Khastagir', 'Both', 'CG0121788L00004', 'মাদক নিয়ে মোটর গাড়ি চালানো', '3 Months', '2000', 'Second Timer', 'Motorcycle', 'CHATTA METRO-DHA-81-0882', 'Second Timer', '3252163104', '24', '', 'Pending', 'Chattogram', '2022-08-02', '21:8', 'ghjj', 'None', '08/28/22'),
(85, 'Muhammad Ali Haider', 'Driver', 'DK0121988M00008', 'বিপজ্জনক বা অননুমোদিত ওভারটেকিং', '15 Days', '10000', 'Second Timer', 'Motorcar', 'CHATTA METRO-DHA-81-0882', 'Second Timer', '5255165104', '27182927', '', 'Paid', 'Chattogram', '2022-08-02', '21:33', 'cjlk', 'None', '08/27/22'),
(86, 'Ashik Siddiquee', 'Owner', ' ', 'ফিটনেস সার্টিফিকেট নেই।', '3 Months', '25000', 'Second Timer', 'Motorcycle', 'CHATTA METRO-GA-79-0120', 'Second Timer', '9012748517', '24', '', 'Pending', 'Chattogram', '2022-08-02', '22:21', 'fjlf', 'None', '08/28/22'),
(87, 'Chinmoy Khastagir', 'Both', 'CG0121788L00004', 'মাদক নিয়ে মোটর গাড়ি চালানো', '3 Months', '2000', 'Second Timer', 'Motorcycle', 'CHATTA METRO-DHA-81-0882', 'Second Timer', '3252163104', '27182927', '', 'Pending', 'Chattogram', '2022-08-02', '22:23', 'dkgd', 'None', '08/02/22'),
(88, 'Chinmoy Khastagir', 'Both', 'CG0121788L00004', 'রেজিস্ট্রেশন ছাড়া মোটরযান চালানো রেজিস্ট্রেশন থাকা সত্ত্বেও নম্বর প্লেট যোগ ও প্রদর্শন না করেই চালান।', '6 Months', '50000', 'Second Timer', 'Motorcycle', 'CHATTA METRO-DHA-81-0882', 'Second Timer', '3252163104', '24', '', 'Pending', 'Chattogram', '2022-08-02', '22:24', 'fokc', 'None', '08/28/22'),
(89, 'Chinmoy Khastagir', 'Both', 'CG0121788L00004', 'কর্তৃপক্ষ কর্তৃক নির্ধারিত রঙ পরিবর্তন।', '6 Months', '300000', 'Second Timer', 'Motorcycle', 'CHATTA METRO-DHA-81-0882', 'Second Timer', '3252163104', '24', '', 'Pending', 'Chattogram', '2022-08-02', '16:26', 'flhf', 'None', '08/21/22'),
(90, 'Chinmoy Khastagir', 'Both', 'CG0121788L00004', 'চাকার আকার, প্রকৃতি পরিবর্তন।', '1 Month', '300000', 'Other', 'Motorcycle', 'CHATTA METRO-DHA-81-0882', 'Other', '3252163104', '27182927', '', 'Paid', 'Chattogram', '2022-08-04', '18:47', 'kazirdeurie ', 'None', '08/25/22'),
(91, 'Ashik Siddiquee', 'Owner', ' ', 'অনুমোদিত ওজন থেকে অতিরিক্ত ওজন ব্যবহার।', '1 Month', '100000', 'Other', 'Motorcar', 'CHATTA METRO-GA-79-0120', 'Other', '9012748517', '24', '', 'Paid', 'Chattogram', '2022-08-04', '18:57', 'Kazirdeurie ', 'None', '08/25/22'),
(92, 'Mohammed Hanif Siddiquee', 'Driver', 'CG9028719H34213', 'চালক সিটবেল্ট পড়েননি', 'None', '1000', 'First Timer', 'Ambulance', 'CHATTA METRO-CHA-77-0998', 'First Timer', '1594121948514', '27182657', '', 'Paid', 'Chattogram', '2022-08-05', '19:5', 'Mohammadpur, Muradpur, Chattogram', 'None', '08/20/22');

-- --------------------------------------------------------

--
-- Table structure for table `crime`
--

CREATE TABLE `crime` (
  `id` int(11) NOT NULL,
  `section` varchar(200) NOT NULL,
  `crime_type` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `imprisonment` varchar(1000) DEFAULT NULL,
  `fine` varchar(1000) DEFAULT NULL,
  `first_fine` varchar(1000) DEFAULT NULL,
  `next_fine_s` varchar(20) NOT NULL,
  `vehicle_type` varchar(200) DEFAULT NULL,
  `next_fine` varchar(1000) DEFAULT NULL,
  `first_fine_b` varchar(20) DEFAULT NULL,
  `vehicle_type_b` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `crime`
--

INSERT INTO `crime` (`id`, `section`, `crime_type`, `imprisonment`, `fine`, `first_fine`, `next_fine_s`, `vehicle_type`, `next_fine`, `first_fine_b`, `vehicle_type_b`) VALUES
(17, '66', 'ড্রাইভিং লাইসেন্স নেই।', '6 months', '25000', '5000', '25000', '', '10,000', '', ''),
(18, '72', 'রেজিস্ট্রেশন ছাড়া মোটরযান চালানো রেজিস্ট্রেশন থাকা সত্ত্বেও নম্বর প্লেট যোগ ও প্রদর্শন না করেই চালান।', '6 months', '50000', '10000', '50000', '', '20000', '', ''),
(19, '75', 'ফিটনেস সার্টিফিকেট নেই।', '6 months', '25000', '10000', '25000', '', '20000', '', ''),
(20, '84', 'অনুমতি ছাড়া বাম স্টিয়ারিং ব্যবহার. মোটর গাড়ির দৈর্ঘ্য, প্রস্থ, উচ্চতা পরিবর্তন করা হয়েছে।', '1-3 years', '300000', '15000', '300000', '', '30000', '', ''),
(21, '86', 'অনুমোদিত ওজন থেকে অতিরিক্ত ওজন ব্যবহার।', '1 years', '100000', '10000', '100000', '', '20000', '', ''),
(22, '87', 'কর্তৃপক্ষ কর্তৃক নির্ধারিত গতিসীমার বেশি গাড়ি চালানো। বেপরোয়াভাবে গাড়ি চালানো।', '3 month', '10000', '2500', '10000', '', '5000', '', ''),
(23, '89(1)', 'অত্যধিক মাত্রার পরিবেশ দূষণকারী ধোঁয়া বা অন্য কোনো ধরনের নির্গমনের ব্যবহার।', '3 month', '25000', '2500', '25000', 'Small car', '10000', ' 5000', 'Large car'),
(24, '89.2', 'ত্রুটিপূর্ণ, ঝুঁকিপূর্ণ বা নিষিদ্ধ ড্রাইভিং। রাস্তা ব্যবহারের জন্য অনুপযুক্ত যানবাহন (নসিমন, করিমন, ভোদভাদি, ইজি বাইক বা অনুরূপ থ্রি-হুইলার)।', '3 month', '25000', '2500', '5000', 'Small car', '10000', '5000', 'Large car'),
(25, '92(1)', 'মাদক নিয়ে মোটর গাড়ি চালানো', '3 month', '10000', '1000', '2000', 'Motorcycles and three-wheelers', '6000', '3000', 'Others vehicle'),
(26, '92(2)', 'গাড়ি চালানোর সময় ফোন বা অনুরূপ সরঞ্জাম ব্যবহার করুন। চালক সিটবেল্ট পড়েননি।', '1 months', '5000', '1000', '5000', 'Motorcycles and three-wheelers', '3000', '1500', 'Others vehicle'),
(27, '95', 'সড়ক দুর্ঘটনার ক্ষেত্রে, চালক/কন্টাক্টর তাদের প্রতিনিধিকে নিকটস্থ পুলিশ স্টেশন/ফায়ার সার্ভিসে অবহিত করেননি এবং আহত ব্যক্তিদের স্বাস্থ্যকেন্দ্রে পাঠাননি এবং চিকিৎসা ব্যবহার করেননি।', '1 months', '20000', '5000', '20000', '', '10000', '', ''),
(39, '66', 'মেয়াদোত্তীর্ণ লাইসেন্স ব্যবহার করার জন্য।', '6 months', '25000', '5000', '25000', '', '10000', '', ''),
(40, '66', 'বিভিন্ন শ্রেণীর লাইসেন্সের ব্যবহার পেশাগত লাইসেন্স ছাড়া পাবলিক ট্রান্সপোর্ট চালান।', '6 months', '25000', '5000', '25000', '', '10000', '', ''),
(42, '75', 'জরাজীর্ণ, বিবর্ণ বা দূষণকারী যানবাহন।', '6 months', '25000', '10000', '25000', '', '20000', '', ''),
(43, '84', 'আসন বিন্যাস পরিবর্তন', '1-3 years', '300000', '15000', '300000', '', '30000', '', ''),
(44, '84', 'চাকার আকার, প্রকৃতি পরিবর্তন।', '1-3 years', '300000', '15000', '300000', '', '30000', '', ''),
(45, '84', 'ব্রেক এবং স্টিয়ারিং গিয়ার, হর্ন, নিরাপত্তা গ্যাস, সিগন্যালিং লাইট, স্পিড গভর্নর পরিবর্তন', '1-3 years', '300000', '15000', '300000', '', '30000', '', ''),
(46, '84', 'ধোঁয়া নির্গমন সিস্টেম এবং কার্বন নির্গমন মাত্রা পরিবর্তন', '1-3 years', '300000', '15000', '300000', '', '30000', '', ''),
(47, '84', 'মোটরযান পরিবর্তনের অনুমতি না পাওয়ার জন্য।', '1-3 years', '300000', '15000', '300000', '', '30000', '', ''),
(48, '84', 'কর্তৃপক্ষ কর্তৃক নির্ধারিত রঙ পরিবর্তন।', '1-3 years', '300000', '15000', '300000', '', '30000', '', ''),
(49, '87', 'বিপজ্জনক বা অননুমোদিত ওভারটেকিং', '3 month', '10000', '2500', '10000', '', '5000', '', ''),
(50, '87', 'মোটরযান চলাচলে প্রতিবন্ধকতা সৃষ্টি করছে।', '3 month', '10000', '2500', '10000', '', '5000', '', ''),
(52, '92(1)', 'বিপরীত দিকে গাড়ি চালানোর জন্য', '3 month', '10000', '1000', '10000', 'Motorcycles and three-wheelers', '6000', '3000', 'Others vehicle'),
(53, '92(1)', 'গাড়ি থামিয়ে প্রতিবন্ধকতা বা যানজট সৃষ্টি করা।', '3 month', '10000', '1000', '10000', 'Motorcycles and three-wheelers', '6000', '3000', 'Others vehicle'),
(54, '92(1)', 'হেলমেট না পরার জন্য।', '3 month', '10000', '1000', '10000', 'Motorcycles and three-wheelers', '6000', '3000', 'Others vehicle'),
(55, '92(1)', 'চলাচলের সময় যাত্রীদের ড্রপ করার জন্য।', '3 month', '10000', '1000', '10000', 'Motorcycles and three-wheelers', '6000', '3000', 'Others vehicle'),
(56, '92(1)', 'প্রতিবন্ধী যাত্রীদের জন্য সুবিধা না থাকা। বড়ির বাইরে বা ছাদে যাত্রী বা মালামাল বহন করা।', '3 month', '10000', '1000', '2000', 'Motorcycles and three-wheelers', '6000', '3000', 'Others vehicle'),
(57, '92(1)', 'ফুটপাতে গাড়ি চালানোর জন্য। মালিকের অনুমতি ছাড়া গাড়ি থেকে বের হওয়া।', '3 month', '10000', '1000', '2000', 'Motorcycles and three-wheelers', '6000', '3000', 'Others vehicle'),
(59, '92(2)', 'চালক সিটবেল্ট পড়েননি', '1 months', '5000', '1000', '2000', 'Motorcycles and three-wheelers', '3000', '1500', 'Others vehicle'),
(60, '92(2)', 'দূরপাল্লার মোটরযানে অতিরিক্ত যাত্রী পরিবহন', '1 months', '5000', '1000', '2000', 'Motorcycles and three-wheelers', '3000', '1500', 'Others vehicle'),
(61, '92(2)', 'মহিলা, শিশু, প্রতিবন্ধী, বয়স্ক যাত্রী এবং অন্যান্য যাত্রীদের জন্য সংরক্ষিত আসনে আসন।', '1 months', '5000', '1000', '2000', 'Motorcycles and three-wheelers', '3000', '1500', 'Others vehicle'),
(62, '92(2)', 'চালক, কন্টাক্টরের যাত্রীদের সাথে অসম্মানজনক আচরণ।', '1 months', '5000', '1000', '2000', 'Motorcycles and three-wheelers', '3000', '1500', 'Others vehicle');

-- --------------------------------------------------------

--
-- Table structure for table `driving_license`
--

CREATE TABLE `driving_license` (
  `id` int(11) NOT NULL,
  `ref_no` varchar(255) NOT NULL,
  `type_of_app` varchar(255) NOT NULL,
  `dctb_serial` varchar(255) NOT NULL,
  `type_of_license` varchar(255) NOT NULL,
  `velicle_class` varchar(255) NOT NULL,
  `NID_No` varchar(500) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `user_photo` varchar(500) DEFAULT NULL,
  `fathers_name` varchar(255) NOT NULL,
  `mothers_name` varchar(255) NOT NULL,
  `spouse_name` varchar(255) NOT NULL,
  `dob` varchar(255) NOT NULL,
  `card_delivery_date` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `ref_date` varchar(255) NOT NULL,
  `ref_expiry_date` varchar(500) NOT NULL,
  `application` varchar(255) NOT NULL,
  `dctb_date` varchar(255) NOT NULL,
  `issue_authority` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `driving_license`
--

INSERT INTO `driving_license` (`id`, `ref_no`, `type_of_app`, `dctb_serial`, `type_of_license`, `velicle_class`, `NID_No`, `name`, `user_photo`, `fathers_name`, `mothers_name`, `spouse_name`, `dob`, `card_delivery_date`, `address`, `ref_date`, `ref_expiry_date`, `application`, `dctb_date`, `issue_authority`) VALUES
(8, 'CG0121788L00004', 'DL Issue', '300', 'Non-Professional', 'Motorcycle, Light', '3252163104', 'Chinmoy Khastagir', 'chinmoy.jpeg', 'Arun Khastagir', 'Gaiyatri Khastagir', 'N/A', '1989-07-11', '2021-10-10', 'Sadarghat, Chattogram', '2021-09-10', '2031-09-10', 'General', '2021-07-11', 'BRTA, Chattogram'),
(9, 'CG7906987M03117', 'DL Issue', '415', 'Professional', 'Motorcar, Heavy', '6446423524', 'Joyraz Barua', 'joyraz.jpeg', 'Suresh Kumar Barua', 'Priti Kana Mutsaddi', 'N/A', '1999-07-03', '2019-09-21', 'West Raozan, Ramjan Alir Hat, Chattogram', '2019-07-13', '2024-07-13', 'General', '2019-05-18', 'BRTA, Chattogram'),
(10, 'DK0121988M00008', 'DL Issue', '730', 'Professional', 'Intercity Bus, Heavy', '5255165104', 'Muhammad Ali Haider', 'ali.jpeg', 'Muhammad Jamil Haider', 'Ruksana Begunm', 'Dola Haider', '1995-07-11', '2021-10-10', 'Mymensingh, Dhaka', '2021-09-10', '2026-09-10', 'General', '2021-07-11', 'BRTA, Dhaka'),
(12, 'CG9028719H34213', 'DL Issue', '419', 'Professional', 'Motorcar, Heavy', '1594121948514', 'Mohammed Hanif Siddiquee', 'hanif.jpeg', 'Md.Haroon', 'Zobaid Ara Begum', 'N/A', '1980-09-01', '2019-09-21', 'House no.36, Jamal Khan Lane,Chattogram.', '2019-07-13', '2024-07-13', 'General', '2019-05-18', 'BRTA, Chattogram'),
(13, 'CG1782901M12987', 'DL Issue', '420', 'Professional', 'Motorcar, Medium', '1517475707544', 'Romel Barua', 'romel.jpeg', 'Suresh Kumar Barua', 'Priti Kana Mutsaddi', 'N/A', '1986-01-01', '2019-09-21', 'West Raozan, Ramjan Alir Hat, Chattogram', '2019-07-13', '2024-07-13', 'General', '2019-05-18', 'BRTA, Chattogram');

-- --------------------------------------------------------

--
-- Table structure for table `emergency_number`
--

CREATE TABLE `emergency_number` (
  `id` int(222) NOT NULL,
  `owner_name` varchar(222) NOT NULL,
  `number` varchar(222) NOT NULL,
  `contact_name` varchar(222) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `emergency_number`
--

INSERT INTO `emergency_number` (`id`, `owner_name`, `number`, `contact_name`) VALUES
(17, 'ashik', '0312858238', 'MD HAROON'),
(21, 'ali', '01784200859', 'joyraz'),
(23, 'chinmoy', '01716065661', 'arun');

-- --------------------------------------------------------

--
-- Table structure for table `learner_card_regi`
--

CREATE TABLE `learner_card_regi` (
  `id` int(11) NOT NULL,
  `learner_no` varchar(255) DEFAULT NULL,
  `application_id` varchar(255) DEFAULT NULL,
  `application_name` varchar(255) DEFAULT NULL,
  `fathers_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dob` varchar(255) DEFAULT NULL,
  `blood_group` varchar(255) DEFAULT NULL,
  `phone_no` varchar(255) DEFAULT NULL,
  `license_type` varchar(255) DEFAULT NULL,
  `vehicle` varchar(255) DEFAULT NULL,
  `expiry_date` varchar(255) DEFAULT NULL,
  `issue_date` varchar(255) DEFAULT NULL,
  `area` varchar(255) DEFAULT NULL,
  `test_name` varchar(255) DEFAULT NULL,
  `test_time` varchar(255) DEFAULT NULL,
  `test_date` varchar(255) DEFAULT NULL,
  `test_value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `learner_card_regi`
--

INSERT INTO `learner_card_regi` (`id`, `learner_no`, `application_id`, `application_name`, `fathers_name`, `address`, `dob`, `blood_group`, `phone_no`, `license_type`, `vehicle`, `expiry_date`, `issue_date`, `area`, `test_name`, `test_time`, `test_date`, `test_value`) VALUES
(1, '0120210', '223', 'ajit2', 'bokul', 'maizbhander', '', 'o+', '123456', '123', 'bike', '2020-12-12', '2020-12-12', 'maizbhander ', 'test22', 'test', 'test', 'test'),
(2, '12345', '123', 'test', 'test', 'test', '2020-10-20', 'o+', '123456', 'test', 'test', '1010-10-20', '1010-10-10', 'test', 'test', 'test', 'test', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `national_identity_table`
--

CREATE TABLE `national_identity_table` (
  `id` int(11) NOT NULL,
  `Name` varchar(500) DEFAULT NULL,
  `Father` varchar(500) DEFAULT NULL,
  `Mother` varchar(500) DEFAULT NULL,
  `Date_of_birth` varchar(500) DEFAULT NULL,
  `NID_No` varchar(500) DEFAULT NULL,
  `photo` varchar(500) DEFAULT NULL,
  `Address` varchar(500) DEFAULT NULL,
  `Blood_group` varchar(500) DEFAULT NULL,
  `Issue_Date` varchar(500) DEFAULT NULL,
  `Place_of_Birth` varchar(500) DEFAULT NULL,
  `isUsed` varchar(22) NOT NULL DEFAULT 'No'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `national_identity_table`
--

INSERT INTO `national_identity_table` (`id`, `Name`, `Father`, `Mother`, `Date_of_birth`, `NID_No`, `photo`, `Address`, `Blood_group`, `Issue_Date`, `Place_of_Birth`, `isUsed`) VALUES
(5, 'Chinmoy Khastagir', 'Arun Khastagir', 'Gaiyatri Khastagir', '1989-11-07', '3252163104', 'chinmoy.jpeg', 'Sadarghat, Chattogram', 'A+', '2019-08-11', 'Chattogram', 'true'),
(6, 'Joyraz Barua', 'Suresh Kumar Barua', 'Priti Kana Mutsaddi', '1999-07-03', '6446423524', 'joyraz.jpeg', 'West Raozan, Ramjan Alir Hat, Chattogram', 'B+', '2018-07-12', 'Chattogram', 'true'),
(7, 'Ashik Siddiquee', 'Md. Haroon', 'Zobaid Ara Begum', '1992-12-17', '9012748517', 'ashik.jpeg', 'Biman Ofiice, Kazir Dewri, Chattogram', 'B+', '2017-04-13', 'Chattogram', 'true'),
(8, 'Md. Selim Ahmed', 'Md. Belal Ahmed', 'Hosneara Begum', '1980-04-19', '7281738901', 'selim.jpg', 'Chawkbazar, Chattogram', 'AB+', '2014-03-20', 'Chattogram', 'true'),
(9, 'Muhammad Ali Haider', 'Muhammad Jamil Haider', 'Ruksana Begum', '1995-11-07', '5255165104', 'ali.jpeg', 'Mymensingh, Dhaka', 'B+', '2019-08-11', 'Mymensingh, Dhaka', 'true'),
(10, 'Farhana Alam Nipa', 'Abu Monsur Alam', 'Zinnat Ara Alam', '1987-12-04', '6120925110', 'nipa.jpeg', 'Dargah Gate, Sylhet', 'A-', '2017-04-12', 'Dargah Gate, Sylhet', 'true'),
(13, 'Md. Haroon', 'Siddique Ahmed', 'Samsun Nahar Begum', '1956-12-31', '7769347944', 'haroon.jpeg', 'House no.36, Jamal Khan Lane,Chattogram.', 'A+', '2017-04-30', 'Chattogram', 'true'),
(14, 'Zobaid Ara Begum', 'Md. Nurul Ghani', 'Nurjahan Begum', '1959-10-12', '1594121948223', 'zobaidara.jpeg', 'House no.36, Jamal Khan Lane,Chattogram.', 'B+', '2019-08-11', 'Chattogram', 'true'),
(15, 'Mohammed Hanif Siddiquee', 'Md.Haroon', 'Zobaid Ara Begum', '1980-09-01', '1594121948514', 'hanif.jpeg', 'House no.36, Jamal Khan Lane,Chattogram.', 'B+', '2014-08-11', 'Chattogram', 'true'),
(16, 'Suresh Kumar Barua', 'Paristosh Barua', 'Sohagi Barua', '1954-11-30', '1517475707551', 'suresh.jpeg', 'West Raozan, Ramjan Alir Hat, Chattogram', 'AB+', '2008-12-01', 'Chattogram', 'no'),
(17, 'Romel Barua', 'Suresh Kumar Barua', 'Priti Kana Mutsaddi', '1986-01-01', '1517475707544', 'romel.jpeg', 'West Raozan, Ramjan Alir Hat, Chattogram', 'AB+', '2008-12-01', 'Chattogram', 'true');

-- --------------------------------------------------------

--
-- Table structure for table `police`
--

CREATE TABLE `police` (
  `police_id` int(222) NOT NULL,
  `police_name` varchar(222) NOT NULL,
  `police_unique_id` varchar(122) NOT NULL,
  `police_department` varchar(222) NOT NULL,
  `police_designation` varchar(111) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `police`
--

INSERT INTO `police` (`police_id`, `police_name`, `police_unique_id`, `police_department`, `police_designation`) VALUES
(1, 'Md. Selim Ahmed', '27182927', 'Traffic', 'Sergeant'),
(2, 'Tonmoy Dev', '27182928', 'Traffic', 'Sergeant'),
(3, 'Romel Barua', '27182657', 'Traffic', 'Sergeant');

-- --------------------------------------------------------

--
-- Table structure for table `unique_id`
--

CREATE TABLE `unique_id` (
  `uni_id` int(11) NOT NULL,
  `unique_value` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `unique_id`
--

INSERT INTO `unique_id` (`uni_id`, `unique_value`) VALUES
(1, '150195');

-- --------------------------------------------------------

--
-- Table structure for table `upload_img`
--

CREATE TABLE `upload_img` (
  `id` int(11) NOT NULL,
  `image` varchar(255) NOT NULL,
  `owner_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `upload_img`
--

INSERT INTO `upload_img` (`id`, `image`, `owner_id`) VALUES
(65, '1272366577', 25),
(66, '1850004824', 25),
(67, '1076502664', 25),
(68, '170515894', 26),
(69, '114847757', 27),
(70, '1330064035', 27),
(71, '1251448794', 27),
(72, '883971112', 28);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `date_of_birth` varchar(500) DEFAULT NULL,
  `Blood_group` varchar(500) DEFAULT NULL,
  `Role` varchar(500) DEFAULT NULL,
  `Driving_license_no` varchar(500) DEFAULT NULL,
  `Vehicle_reg_no` varchar(500) DEFAULT NULL,
  `user_name` varchar(500) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  `photo_name` varchar(500) DEFAULT NULL,
  `NID_No` varchar(500) DEFAULT NULL,
  `phone` varchar(500) DEFAULT NULL,
  `division` varchar(500) DEFAULT NULL,
  `Police_id` varchar(600) DEFAULT NULL COMMENT 'Batch no of police'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `date_of_birth`, `Blood_group`, `Role`, `Driving_license_no`, `Vehicle_reg_no`, `user_name`, `password`, `photo_name`, `NID_No`, `phone`, `division`, `Police_id`) VALUES
(22, 'Chinmoy Khastagir', '1989-11-07', 'A+', 'Both', 'CG0121788L00004', 'CHATTA METRO-DHA-81-0882', 'chinmoy', '1234', 'chinmoy.jpeg', '3252163104', '01306723654', 'Sadarghat, Chittagong', 'Not Applicable'),
(24, 'Md. Selim Ahmed', '1980-04-19', 'AB+', 'Traffic Police', 'Not Applicable', 'Not Applicable', 'sergeant_selim', '1234', 'selim.jpg', '7281738901', '01581143606', 'Chawkbazar, Chattogram', '27182927'),
(27, 'Ashik Siddiquee', '1992-12-17', 'B+', 'Owner', 'Not Applicable', 'CHATTA METRO-GA-79-0120', 'ashik', '1234', 'ashik.jpeg', '9012748517', '01681787479', 'Biman Ofiice, Kazir Dewri, Chattogram', 'Not Applicable'),
(28, 'Muhammad Ali Haider', '1995-11-07', 'B+', 'Driver', 'DK0121988M00008', 'Not Applicable', 'ali', '1234', 'ali.jpeg', '5255165104', '01793764561', 'Mymensingh, Dhaka', 'Not Applicable'),
(29, 'Farhana Alam Nipa', '1987-12-04', 'A-', 'Owner', 'Not Applicable', 'SYLHET METRO-MA-97-0101', 'nipa', '1234', 'nipa.jpeg', '6120925110', '01784200852', 'Dargah Gate, Sylhet', 'Not Applicable'),
(33, 'Joyraz Barua', '1999-07-03', 'B+', 'Driver', 'CG7906987M03117', 'Not Applicable', 'joyraz', '1234', 'joyraz.jpeg', '6446423524', '01784200859', 'West Raozan, Ramjan Alir Hat, Chattogram', 'Not Applicable'),
(35, 'Mohammed Hanif Siddiquee', '1980-09-01', 'B+', 'Driver', 'CG9028719H34213', 'Not Applicable', 'md_hanif', '1234567890asdfg', 'hanif.jpeg', '1594121948514', '01720656775', 'House no.36, Jamal Khan Lane,Chattogram.', 'Not Applicable'),
(36, 'Romel Barua', '1986-01-01', 'AB+', 'Traffic Police', 'Not Applicable', 'Not Applicable', 'romel_barua', '1234567890asdfg', 'romel.jpeg', '1517475707544', '01716021335', 'West Raozan, Ramjan Alir Hat, Chattogram', '27182657');

-- --------------------------------------------------------

--
-- Table structure for table `vehiclefeesindex`
--

CREATE TABLE `vehiclefeesindex` (
  `id` int(11) NOT NULL,
  `Registration_no` varchar(1000) DEFAULT NULL COMMENT 'Vehicle no',
  `Ownership_type` varchar(1000) DEFAULT NULL,
  `CC` varchar(1000) DEFAULT NULL,
  `Tax_token_expire` varchar(1000) DEFAULT NULL,
  `Fitness_expire` varchar(1000) DEFAULT NULL,
  `Road_permit_expire` varchar(1000) DEFAULT NULL,
  `Rule` varchar(1000) DEFAULT NULL,
  `Main_Fees` varchar(1000) DEFAULT NULL,
  `Extra_price` varchar(1000) DEFAULT NULL,
  `Inspection_fee` varchar(1000) DEFAULT NULL,
  `Label_fees` varchar(1000) DEFAULT NULL,
  `Application_fee` varchar(1000) DEFAULT NULL,
  `Delay_fines` varchar(1000) DEFAULT NULL,
  `Other_fees` varchar(1000) DEFAULT NULL,
  `VAT` varchar(1000) DEFAULT NULL,
  `Total_fees` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vehiclefeesindex`
--

INSERT INTO `vehiclefeesindex` (`id`, `Registration_no`, `Ownership_type`, `CC`, `Tax_token_expire`, `Fitness_expire`, `Road_permit_expire`, `Rule`, `Main_Fees`, `Extra_price`, `Inspection_fee`, `Label_fees`, `Application_fee`, `Delay_fines`, `Other_fees`, `VAT`, `Total_fees`) VALUES
(1, 'DHAKA METRO-GA-19-1001', 'PRIVATE', '1500', '30-MAY-22', '16-JUN-22', 'N/A', 'RENEWAL OF TAX TOKEN', '5,000', '0', '0', '45', '0', '0', '0', '757', '5,802'),
(2, 'DHAKA METRO-GA-19-1001', 'PRIVATE', '1500', '30-MAY-22', '16-JUN-22', 'N/A', 'ADVANCE INCOME TAX (PERSONAL)', '25,000', '0', '0', '0', '0', '0', '0', '0', '25,000'),
(3, 'DHAKA METRO-GA-19-1001', 'PRIVATE', '1500', '30-MAY-22', '16-JUN-22', 'N/A', 'RENEWAL OF FITNESS CERTIFICATE', '450', '0', '450', '45', '0', '0', '0', '142', '1,087'),
(4, 'DHAKA METRO-GA-19-1001', 'PRIVATE', '1500', '30-MAY-22', '16-JUN-22', 'N/A', 'RENEWAL OF FITNESS CERTIFICATE 2ND YEAR', '450', '0', '0', '0', '0', '0', '0', '68', '518'),
(5, 'DHAKA METRO-GA-19-1001', 'PRIVATE', '1500', '30-MAY-22', '16-JUN-22', 'N/A', 'SUPPLEMENTARY DUTY', '210', '0', '0', '0', '0', '0', '0', '32', '242'),
(6, 'CHATTA METRO-HA-11-6290', 'PRIVATE', '100', '30-MAY-22', '16-JUN-22', 'N/A', 'RENEWAL OF TAX TOKEN', '5,000', '0', '0', '45', '0', '0', '0', '757', '5,802'),
(7, 'CHATTA METRO-HA-11-6290', 'PRIVATE', '100', '30-MAY-22', '16-JUN-22', 'N/A', 'ADVANCE INCOME TAX (PERSONAL)', '25,000', '0', '0', '0', '0', '0', '0', '0', '25,000'),
(8, 'CHATTA METRO-HA-11-6290', 'PRIVATE', '100', '30-MAY-22', '16-JUN-22', 'N/A', 'RENEWAL OF FITNESS CERTIFICATE', '450', '0', '450', '45', '0', '0', '0', '142', '1,087'),
(9, 'CHATTA METRO-HA-11-6290', 'PRIVATE', '100', '30-MAY-22', '16-JUN-22', 'N/A', 'RENEWAL OF FITNESS CERTIFICATE 2ND YEAR', '450', '0', '0', '0', '0', '0', '0', '68', '518'),
(10, 'CHATTA METRO-HA-11-6290', 'PRIVATE', '100', '30-MAY-22', '16-JUN-22', 'N/A', 'SUPPLEMENTARY DUTY', '210', '0', '0', '0', '0', '0', '0', '32', '242'),
(11, 'CHATTA METRO-HA-11-1919', 'PRIVATE', '100', '30-NOV-22', '16-JUL-23', 'N/A', 'RENEWAL OF TAX TOKEN', '1,000', '0', '0', '45', '0', '0', '0', '757', '1,802'),
(12, 'CHATTA METRO-HA-11-1919', 'PRIVATE', '100', '30-NOV-22', '16-JUL-23', 'N/A', 'ADVANCE INCOME TAX (PERSONAL)', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
(13, 'CHATTA METRO-HA-11-1919', 'PRIVATE', '100', '30-NOV-22', '16-JUL-23', 'N/A', 'RENEWAL OF FITNESS CERTIFICATE', '500', '0', '0', '45', '0', '0', '0', '142', '642'),
(14, 'CHATTA METRO-HA-11-1919', 'PRIVATE', '100', '30-NOV-22', '16-JUL-23', 'N/A', 'RENEWAL OF FITNESS CERTIFICATE 2ND YEAR', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
(15, 'CHATTA METRO-HA-11-1919', 'PRIVATE', '100', '30-NOV-22', '16-JUL-23', 'N/A', 'SUPPLEMENTARY DUTY', '250', '0', '0', '0', '0', '0', '0', '32', '282'),
(16, 'CHATTA METRO-HA-11-1122', 'PRIVATE', '100', '30-NOV-23', '16-JUL-26', 'N/A', 'None', '0', '0', '0', '0', '0', '0', '0', '0', '0'),
(17, 'CHATTA METRO-HA-11-6156', 'PRIVATE', '100', '31-DEC-25', '12-JUL-25', 'N/A', 'None', '0', '0', '0', '0', '0', '0', '0', '0', '0');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle_letter`
--

CREATE TABLE `vehicle_letter` (
  `lid` int(11) NOT NULL,
  `letter` varchar(255) NOT NULL,
  `vehicle_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle_letter`
--

INSERT INTO `vehicle_letter` (`lid`, `letter`, `vehicle_id`) VALUES
(1, 'এ', 1),
(2, 'হ', 1),
(3, 'ল', 1),
(4, 'ক', 2),
(5, 'খ', 2),
(6, 'গ', 2),
(7, 'ভ', 2),
(8, 'ঘ', 3),
(9, 'ঘ', 4),
(10, 'চ', 5),
(11, 'চ', 6),
(12, 'প', 7),
(13, 'ছ', 8),
(14, 'ছ', 9),
(15, 'ছ', 10),
(16, 'জ', 11),
(17, 'ঝ', 12),
(18, 'ব', 13),
(19, 'ব', 14),
(20, 'স', 15),
(21, 'স', 16),
(22, 'ত', 17),
(23, 'থ', 17),
(24, 'দ', 17),
(25, 'ফ', 18),
(26, 'ঠ', 19),
(27, 'ম', 20),
(28, 'ম', 21),
(29, 'ন', 22),
(30, 'ন', 23),
(31, 'ন', 24),
(32, 'ন', 25),
(33, 'ন', 26),
(34, 'ন', 27),
(35, 'ন', 28),
(36, 'ন', 29),
(37, 'অ', 22),
(38, 'অ', 23),
(39, 'অ', 24),
(40, 'অ', 25),
(41, 'অ', 26),
(42, 'অ', 27),
(43, 'অ', 28),
(44, 'অ', 29),
(45, 'ড', 22),
(46, 'ড', 23),
(47, 'ড', 24),
(48, 'ড', 25),
(49, 'ড', 26),
(50, 'ড', 27),
(51, 'ড', 28),
(52, 'ড', 29),
(53, 'উ', 22),
(54, 'উ', 23),
(55, 'উ', 24),
(56, 'উ', 25),
(57, 'উ', 26),
(58, 'উ', 27),
(59, 'উ', 28),
(60, 'উ', 29),
(61, 'ট', 22),
(62, 'ট', 23),
(63, 'ট', 24),
(64, 'ট', 25),
(65, 'ট', 26),
(66, 'ট', 27),
(67, 'ট', 28),
(68, 'ট', 29),
(69, 'ঢ', 30),
(70, 'ঢ', 31),
(71, 'ঢ', 32),
(72, 'ঢ', 33),
(73, 'শ', 34),
(74, 'শ', 35),
(75, 'শ', 36),
(76, 'শ', 37),
(77, 'শ', 38),
(78, 'শ', 39),
(79, 'শ', 40),
(80, 'শ', 41),
(81, 'শ', 42),
(82, 'ই', 43),
(83, 'য', 44),
(84, 'র', 45);

-- --------------------------------------------------------

--
-- Table structure for table `vehicle_name`
--

CREATE TABLE `vehicle_name` (
  `id` int(11) NOT NULL,
  `vehicle_type_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle_name`
--

INSERT INTO `vehicle_name` (`id`, `vehicle_type_name`) VALUES
(1, 'Motorcycle '),
(2, 'Motorcar'),
(3, 'Suv'),
(4, 'Crossover'),
(5, 'Microbus'),
(6, 'Mpv '),
(7, 'Taxicab'),
(8, 'Itoman'),
(9, 'hecuter'),
(10, 'Ambulance'),
(11, 'Minibus'),
(12, ' Coach bus'),
(13, 'Intercity bus'),
(14, 'Inter district bus'),
(15, 'School bus'),
(16, 'College bus'),
(17, 'Auto rickshaws(Baby taxi/CNG) '),
(18, 'Auto tempo'),
(19, 'Passenger/panel/prison/security) van'),
(20, 'Delivery van'),
(21, 'Mini truck'),
(22, 'Truck 7 cargo truck'),
(23, 'Dump truck'),
(24, 'Cargo van'),
(25, 'Covered van'),
(26, 'low-bad truck'),
(27, 'bottle Carice'),
(28, 'pole carrier '),
(29, 'rigid truck'),
(30, 'Private articulated vehicle'),
(31, 'oil tanker'),
(32, 'water tanker'),
(33, 'Semi teaiter tank / container chassis gas tanker'),
(34, 'Special purpose vehicle-> cinerator mixer  '),
(35, 'cement bulker '),
(36, 'constructional truck'),
(37, 'Frazer van '),
(38, 'tow truck '),
(39, 'Mobile creme '),
(40, 'sorbose truck '),
(41, 'earth digger '),
(42, 'payloader '),
(43, 'Agricultural vehicle '),
(44, 'prime ministers office (any vehicle)'),
(45, 'president\'s office (any vehicle)');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle_power`
--

CREATE TABLE `vehicle_power` (
  `pid` int(11) NOT NULL,
  `power` varchar(255) NOT NULL,
  `vehicle_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle_power`
--

INSERT INTO `vehicle_power` (`pid`, `power`, `vehicle_id`) VALUES
(1, 'up to 50 cc', '1'),
(2, '51 to 125 cc', '1'),
(3, '126 to 165 cc', '1'),
(4, 'up to 1000 cc', '2'),
(5, '1001 to 1300 cc', '2'),
(6, '1301 to 2000cc', '2'),
(7, 'above 2001 cc', '2'),
(8, 'up to 2.5 tonnes ', '20'),
(9, 'up to 3.5 tonnes ', '22'),
(10, '3.5 to 7.5 tonnes', '22'),
(11, '7.5 to 22 tonnes', '22'),
(12, 'up to 2.5 tonnes ', '21'),
(13, 'up to 3.5 tonnes ', '23'),
(14, '3.5 to 7.5 tonnes', '23'),
(15, '7.5 to 22 tonnes', '23'),
(16, 'up to 3.5 tonnes ', '24'),
(17, '3.5 to 7.5 tonnes', '24'),
(18, '7.5 to 22 tonnes', '24'),
(19, 'up to 3.5 tonnes ', '25'),
(20, '3.5 to 7.5 tonnes', '25'),
(21, '7.5 to 22 tonnes', '25'),
(22, 'up to 3.5 tonnes ', '26'),
(23, '3.5 to 7.5 tonnes', '26'),
(24, '7.5 to 22 tonnes', '26'),
(25, 'up to 3.5 tonnes ', '27'),
(26, '3.5 to 7.5 tonnes', '27'),
(27, '7.5 to 22 tonnes', '27'),
(28, '3.5 to 7.5 tonnes', '28'),
(29, '7.5 to 22 tonnes', '28'),
(30, '7.5 to 22 tonnes', '28'),
(31, '3.5 to 7.5 tonnes', '29'),
(32, '7.5 to 22 tonnes', '29'),
(33, '7.5 to 22 tonnes', '29');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle_registar`
--

CREATE TABLE `vehicle_registar` (
  `id` int(11) NOT NULL,
  `veh_regi_name` varchar(255) NOT NULL,
  `vehi_regi_power` varchar(255) NOT NULL,
  `vehi_regi_letter` varchar(255) NOT NULL,
  `vehi_unique_id` varchar(255) NOT NULL,
  `owner_name` varchar(255) DEFAULT NULL,
  `vehicle_department` varchar(255) DEFAULT NULL,
  `vehicle_fuel` varchar(255) DEFAULT NULL,
  `vehicle_color` varchar(255) DEFAULT NULL,
  `vehicle_cc` varchar(255) DEFAULT NULL,
  `vehicle_seat` varchar(255) DEFAULT NULL,
  `vehicle_engine_no` varchar(255) DEFAULT NULL,
  `chassis_no` varchar(255) DEFAULT NULL,
  `hire` varchar(255) DEFAULT NULL,
  `wheel_base` varchar(255) DEFAULT NULL,
  `unladen` varchar(255) DEFAULT NULL,
  `laden` varchar(255) DEFAULT NULL,
  `issusing_authority` varchar(255) DEFAULT NULL,
  `owner_address` varchar(255) DEFAULT NULL,
  `owner_type` varchar(255) DEFAULT NULL,
  `tyre_size` varchar(255) DEFAULT NULL,
  `responsible` varchar(255) DEFAULT NULL,
  `mfg_year` varchar(255) DEFAULT NULL,
  `front` varchar(255) DEFAULT NULL,
  `middle` varchar(255) DEFAULT NULL,
  `Back` varchar(255) DEFAULT NULL,
  `length` varchar(255) DEFAULT NULL,
  `Width` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `over_front` varchar(255) DEFAULT NULL,
  `over_back` varchar(255) DEFAULT NULL,
  `uploadprofile` varchar(255) NOT NULL,
  `registration_date` varchar(255) DEFAULT NULL,
  `vehicle_metro` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle_registar`
--

INSERT INTO `vehicle_registar` (`id`, `veh_regi_name`, `vehi_regi_power`, `vehi_regi_letter`, `vehi_unique_id`, `owner_name`, `vehicle_department`, `vehicle_fuel`, `vehicle_color`, `vehicle_cc`, `vehicle_seat`, `vehicle_engine_no`, `chassis_no`, `hire`, `wheel_base`, `unladen`, `laden`, `issusing_authority`, `owner_address`, `owner_type`, `tyre_size`, `responsible`, `mfg_year`, `front`, `middle`, `Back`, `length`, `Width`, `height`, `over_front`, `over_back`, `uploadprofile`, `registration_date`, `vehicle_metro`) VALUES
(9, '1', '51 to 125 cc', 'হ', '150104', 'Rana', 'motorcycle', 'petrol', 'black', '100', '2', '1234567', 'zxcfsdgwr245', 'no', '1230', '100', '200', 'CHITTAGONG ', 'Chittagong Maizbhander amtoli', 'Private ', '80/100-47 - 80/100-54', 'NO', '2020', 'kg', 'kg', 'kg', 'MM', 'MM', 'MM', '%', '%', '61b316830c951foodbanner.jpg', '2021-12-17', ''),
(14, '1', '51 to 125 cc', 'হ', '150105', 'Rahul', 'motorcycle', 'petrol', 'black', '100', '2', '1234567', 'zxcfsdgwr245', 'no', '1230', '100', '200', 'CHITTAGONG ', 'Chittagong Maizbhander amtoli', 'Private ', '80/100-47 - 80/100-54', 'NO', '2020', 'kg', 'kg', 'kg', 'MM', 'MM', 'MM', '%', '%', '61b31a92b9212foodbanner.jpg', '2021-12-17', ''),
(20, '6', '', 'চ', '150106', 'korim', 'motorcycle', 'petrol', 'black', '100', '2', '1234567', 'zxcfsdgwr245', 'no', '1230', '100', '200', 'CHITTAGONG ', 'Chittagong Maizbhander amtoli', 'Private ', '80/100-47 - 80/100-54', 'NO', '2020', 'kg', 'kg', 'kg', 'MM', 'MM', 'MM', '%', '%', '61b31e299005ffoodbanner.jpg', '2021-12-17', ''),
(21, '1', 'up to 50 cc', 'এ', '150115', 'Ashik', 'Motorcycle Medium', 'petrol', 'black', '100', '2', '1234567', 'zxcfsdgwr245', 'no', '1230', '100', '200', 'CHITTAGONG ', 'Chittagong Maizbhander amtoli', 'Private ', '80/100-47 - 80/100-54', 'NO', '2020', 'kg', 'kg', 'kg', 'MM', 'MM', 'MM', '%', '%', '61b5cce998f73WIN_20211126_01_03_51_Pro.jpg', '2021-12-12', ''),
(22, '1', '126 to 165 cc', 'হ', '150181', 'ajit', 'test', 'petrol', 'black', '100', '2', '1234567', 'zxcfsdgwr245', 'no', '1230', '100', '200', 'CHITTAGONG ', 'test', 'Private ', '80/100-47 - 80/100-54', 'NO', '2020', 'kg', 'kg', 'kg', 'MM', 'MM', 'MM', 'test', 'test', '61eff71ee25da61b31a92b9212foodbanner.jpg', '2022-12-12', 'test'),
(23, '1', 'up to 50 cc', 'হ', '150187', 'ajit', 'motorcycle', 'petrol', 'black', '100', 'test', '1234567', 'zxcfsdgwr245', 'no', '1230', '100', '200', 'CHITTAGONG ', 'Chittagong Maizbhander amtoli', 'test', '80/100-47 - 80/100-54', 'NO', '2020', 'kg', 'kg', 'kg', 'MM', 'v', 'MM', '%', '%', '620ab71bdb42bchart.png', '2022-02-15', 'test'),
(24, '1', '51 to 125 cc', 'ল', '150188', 'ajit', 'motorcycle', 'petrol', 'black', '100', '2', '1234567', 'zxcfsdgwr245', 'no', '1230', '100', '200', 'CHITTAGONG ', 'Chittagong Maizbhander amtoli', 'Private ', '80/100-47 - 80/100-54', 'NO', '2020', 'test', 'kg', 'kg', 'MM', 'MM', 'MM', '%', '', '620abab689b19chart.png', '2022-02-15', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle_registration_brta`
--

CREATE TABLE `vehicle_registration_brta` (
  `id` int(11) NOT NULL,
  `Registration_No` varchar(500) DEFAULT NULL,
  `Vehicle_Description` varchar(500) DEFAULT NULL,
  `Vehicle_class` varchar(500) DEFAULT NULL,
  `CC` varchar(500) DEFAULT NULL,
  `Color` varchar(500) DEFAULT NULL,
  `Fuel` varchar(500) DEFAULT NULL,
  `Seat` varchar(500) DEFAULT NULL,
  `Engine_No` varchar(500) DEFAULT NULL,
  `Chassis_No` varchar(500) DEFAULT NULL,
  `Hire` varchar(500) DEFAULT NULL,
  `Date` varchar(500) DEFAULT NULL,
  `Wheel_Base` varchar(500) DEFAULT NULL,
  `Issuing_Authority` varchar(500) DEFAULT NULL,
  `Weight_Unladen` varchar(500) DEFAULT NULL,
  `Weight_laden` varchar(500) DEFAULT NULL,
  `NID_No` varchar(5000) DEFAULT NULL COMMENT 'owner''s Nid',
  `photo` varchar(500) DEFAULT NULL,
  `Owner_name` varchar(500) DEFAULT NULL,
  `Owner_address` varchar(500) DEFAULT NULL,
  `Tyre_size` varchar(500) DEFAULT NULL,
  `H.P` varchar(500) DEFAULT NULL,
  `Mfg_Year` varchar(500) DEFAULT NULL,
  `customer_id` varchar(500) NOT NULL,
  `routepermit_certificate_no` varchar(500) NOT NULL,
  `routepermit_no` varchar(500) NOT NULL,
  `route` varchar(500) NOT NULL,
  `routepermit_start_date` varchar(500) NOT NULL,
  `routepermit_expiry_date` varchar(500) NOT NULL,
  `fitness_certificate_id` varchar(500) NOT NULL,
  `fitness_certificate_no` varchar(500) NOT NULL,
  `fitness_certificate_start_date` varchar(500) NOT NULL,
  `fitness_certificate_expiry_date` varchar(500) NOT NULL,
  `taxtoken_no` varchar(500) NOT NULL,
  `taxtoken_transaction_no` varchar(500) NOT NULL,
  `issuing_bank_name` varchar(500) NOT NULL,
  `isuuing_bank_branch` varchar(500) NOT NULL,
  `issuing_teller_name` varchar(500) NOT NULL,
  `taxperiod_start_date` varchar(500) NOT NULL,
  `taxperiod_expiry_date` varchar(500) NOT NULL,
  `principal_amount_paid` varchar(500) NOT NULL,
  `vat` varchar(500) NOT NULL,
  `fine` varchar(500) NOT NULL,
  `total_paid` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vehicle_registration_brta`
--

INSERT INTO `vehicle_registration_brta` (`id`, `Registration_No`, `Vehicle_Description`, `Vehicle_class`, `CC`, `Color`, `Fuel`, `Seat`, `Engine_No`, `Chassis_No`, `Hire`, `Date`, `Wheel_Base`, `Issuing_Authority`, `Weight_Unladen`, `Weight_laden`, `NID_No`, `photo`, `Owner_name`, `Owner_address`, `Tyre_size`, `H.P`, `Mfg_Year`, `customer_id`, `routepermit_certificate_no`, `routepermit_no`, `route`, `routepermit_start_date`, `routepermit_expiry_date`, `fitness_certificate_id`, `fitness_certificate_no`, `fitness_certificate_start_date`, `fitness_certificate_expiry_date`, `taxtoken_no`, `taxtoken_transaction_no`, `issuing_bank_name`, `isuuing_bank_branch`, `issuing_teller_name`, `taxperiod_start_date`, `taxperiod_expiry_date`, `principal_amount_paid`, `vat`, `fine`, `total_paid`) VALUES
(5, 'CHATTA METRO-DHA-81-0882', 'Motorcycle', 'Motorcycle, Light', '100', 'Black', 'Petrol', '2', 'DTHZ411538', 'MB1TLPYB3DRTJ0523', 'No', '2021-09-11', '1345mm', 'BRTA, Chattogram', '150kg', '300kg', '3252163104', 'chinmoy.jpeg', 'Chinmoy Khastagir', 'Sadarghat, Chittagong', 'Front: 90/90 17, Rear: 120/80 17', '13.8', '2018', '4-000834117', 'P-200575341', 'P-04-4-0506267/21', 'Whole of Bangladesh', '2022-01-01', '2025-01-01', '7885158', '4-7695|20/22', '2022-01-01', '2023-01-01', '216748468', '2111021117403', 'Standard Bank Limited', 'Agrabad', 'STD-AG-002', '2022-01-01', '2023-01-01', '8845', '1327', '0', '10172'),
(6, 'CHATTA METRO-GA-79-0120', 'Motorcar', 'Motorcar, Heavy', '1950', 'Blue', 'Diesel', '4', 'AHHJ821437', 'MB1IKOLM3TYHU8759', 'No', '2019-11-11', '2741mm', 'BRTA, Chattogram', '1500kg', '3000kg', '9012748517', 'ashik.jpeg', 'Ashik Siddiquee', 'Biman Ofiice, Kazir Dewri, Chattogram', 'Front: 235/60 R18, Rear: 325/90 28', '13.8', '2015', '7-000764617', 'K-435786910', 'K-09-3-0802901/12', 'Whole of Bangladesh', '2020-02-03', '2023-02-03', '9807189', '3-8291|18/14', '2019-12-01', '2020-12-01', '817910278', '1830192673617', 'UCB Bank Limited', 'G.E.C.', 'UCB-AK-012', '2019-12-01', '2020-12-01', '18845', '3500', '0', '22345'),
(7, 'CHATTA METRO-BHA-78-0029', 'Motorcar', 'Motorcar, Heavy', '1900', 'Green', 'Diesel', '4', 'JKDU873908', 'KL2KSILU5KLIO8397', 'No', '2018-09-18', '2700mm', 'BRTA, Chattogram', '1400kg', '2800kg', '3252163104', 'chinmoy.jpeg', 'Chinmoy Khastagir', 'Sadarghat, Chattogram', 'Front: 245/50 R16, Rear: 335/87 25', '15.8', '2009', '8-000872456', 'K-092846', 'K-09-4-0192897/11', 'Whole of Bangladesh', '2018-10-10', '2021-10-10', '2871902', '6-2987|13/08', '2018-11-10', '2019-11-10', '287190928', '1920394857164', 'Islami Bank Limited', 'Muradpur', 'IB-AK-012', '2018-12-05', '2018-12-05', '19000', '3600', '0', '22600'),
(8, 'SYLHET METRO-MA-97-0101', 'Mini Truck', 'Mini Truck, Light', '909', 'Red', 'Diesel', '2', 'MSPM092817', 'MT4LKSOM3DFRE0927', 'No', '2021-09-11', '1950mm', 'BRTA, Sylhet', '850kg', '1100kg', '6120925110', 'nipa.jpeg', 'Farhana Alam Nipa', 'Dargah Gate, Sylhet', 'Front: 145 R12,8PR, Rear: 145 R12,8PR', '26', '2018', '8-298172893', 'P-283748291', 'P-07-7-2766267/23', 'Whole of Bangladesh', '2022-01-01', '2025-01-01', '2738190', '8-2817|20/22', '2022-01-01', '2023-01-01', '291029389', '1265021123678', 'Prime Bank Limited', 'Dargah Gate, Sylhet', 'PBL-FE-002', '2022-01-01', '2023-01-01', '12345', '2987', '0', '15332'),
(9, 'CHATTA METRO-CHA-77-0998', 'Ambulance', 'Motorcar, Heavy', '2494', 'White', 'Diesel', '4', 'ABLC411440', 'AB1TLPYB3DRTJ0321', 'YES', '2021-09-11', '2432mm', 'BRTA, Chattogram', '300kg', '600kg', '7769347944', 'haroon.jpeg', 'Md. Haroon', 'House no.36, Jamal Khan Lane,Chattogram., ', '195R15C', '89.8', '2018', '4-000834117', 'P-100575300', 'P-04-4-0506100/21', 'Whole of Bangladesh', '2022-01-01', '2025-01-01', '7885158', '4-7695|20/22', '2022-01-01', '2023-01-01', '216742345', '2111021117403', 'NCC Bank Limited', 'Love Lane, Chattogram.', 'NCC-LL-100', '2022-01-01', '2023-01-01', '12345', '3500', '0', '15845'),
(10, 'CHATTA METRO-BHA-84-3019', 'Motorcar', 'Motorcar, Heavy', '1496', 'White', 'Gasoline', '5', 'DBA-NZT260-AEXEK', 'KJ3KHUJB3DRTJ8729', 'No', '2021-09-11', '2700mm', 'BRTA, Chattogram', '250kg', '800kg', '1594121948223', 'zobaidara.jpeg', 'Zobaid Ara Begum', 'House no.36, Jamal Khan Lane,Chattogram.', 'Front: 1480mm, Rear: 1460mm', '140', '2018', '4-000267189', 'P-928775341', 'P-05-6-1986267/21', 'Chattogram', '2022-01-01', '2025-01-01', '1029389', '5-2918|20/22', '2022-01-01', '2023-01-01', '192038192', '2319021111872', 'Sonali Bank Limited', 'G.E.C', 'SBL-TM-002', '2022-01-01', '2023-01-01', '6500', '1190', '0', '7690'),
(11, 'CHATTA METRO-THA-95-2491', 'CNG', 'Auto Rickshaw', '198.8', 'Green', 'Gasoline', '4', 'Twin Spark, 4-stroke DTSi', 'WK5KJILO3DRTJ8276', 'YES', '2021-09-11', '2000mm', 'BRTA, Chattogram', '100kg', '400kg', '1517475707551', 'suresh.jpeg', 'Suresh Kumar Barua', 'West Raozan, Ramjan Alir Hat, Chattogram', 'Front: 4.00-8,4 PR/6PR, Rear: 4.00-8,4 PR/6PR', '14.9', '2018', '4-000232456', 'P-928709287', 'P-08-6-1982765/21', 'Chattogram', '2022-01-01', '2025-01-01', '1029389', '5-2918|20/22', '2022-01-01', '2023-01-01', '192038192', '2319021111872', 'Islami Bank Limited', 'Raozan', 'IBL-RR-187', '2022-01-01', '2023-01-01', '6500', '1190', '0', '7690');

-- --------------------------------------------------------

--
-- Table structure for table `wp_user`
--

CREATE TABLE `wp_user` (
  `id` int(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `wp_user`
--

INSERT INTO `wp_user` (`id`, `user_name`, `email`, `password`) VALUES
(1, 'admin', 'admin@gmail.com', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `add_my_driver`
--
ALTER TABLE `add_my_driver`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bkash_account`
--
ALTER TABLE `bkash_account`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cases`
--
ALTER TABLE `cases`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `crime`
--
ALTER TABLE `crime`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `driving_license`
--
ALTER TABLE `driving_license`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `emergency_number`
--
ALTER TABLE `emergency_number`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `learner_card_regi`
--
ALTER TABLE `learner_card_regi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `national_identity_table`
--
ALTER TABLE `national_identity_table`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `police`
--
ALTER TABLE `police`
  ADD PRIMARY KEY (`police_id`);

--
-- Indexes for table `unique_id`
--
ALTER TABLE `unique_id`
  ADD PRIMARY KEY (`uni_id`);

--
-- Indexes for table `upload_img`
--
ALTER TABLE `upload_img`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_name` (`user_name`,`NID_No`);

--
-- Indexes for table `vehiclefeesindex`
--
ALTER TABLE `vehiclefeesindex`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vehicle_letter`
--
ALTER TABLE `vehicle_letter`
  ADD PRIMARY KEY (`lid`);

--
-- Indexes for table `vehicle_name`
--
ALTER TABLE `vehicle_name`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vehicle_power`
--
ALTER TABLE `vehicle_power`
  ADD PRIMARY KEY (`pid`);

--
-- Indexes for table `vehicle_registar`
--
ALTER TABLE `vehicle_registar`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `vehi_unique_id` (`vehi_unique_id`);

--
-- Indexes for table `vehicle_registration_brta`
--
ALTER TABLE `vehicle_registration_brta`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `wp_user`
--
ALTER TABLE `wp_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `add_my_driver`
--
ALTER TABLE `add_my_driver`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(22) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `cases`
--
ALTER TABLE `cases`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT for table `crime`
--
ALTER TABLE `crime`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT for table `driving_license`
--
ALTER TABLE `driving_license`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `emergency_number`
--
ALTER TABLE `emergency_number`
  MODIFY `id` int(222) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `learner_card_regi`
--
ALTER TABLE `learner_card_regi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `national_identity_table`
--
ALTER TABLE `national_identity_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `police`
--
ALTER TABLE `police`
  MODIFY `police_id` int(222) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `unique_id`
--
ALTER TABLE `unique_id`
  MODIFY `uni_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `upload_img`
--
ALTER TABLE `upload_img`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `vehiclefeesindex`
--
ALTER TABLE `vehiclefeesindex`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `vehicle_letter`
--
ALTER TABLE `vehicle_letter`
  MODIFY `lid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- AUTO_INCREMENT for table `vehicle_name`
--
ALTER TABLE `vehicle_name`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `vehicle_power`
--
ALTER TABLE `vehicle_power`
  MODIFY `pid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `vehicle_registar`
--
ALTER TABLE `vehicle_registar`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `vehicle_registration_brta`
--
ALTER TABLE `vehicle_registration_brta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `wp_user`
--
ALTER TABLE `wp_user`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

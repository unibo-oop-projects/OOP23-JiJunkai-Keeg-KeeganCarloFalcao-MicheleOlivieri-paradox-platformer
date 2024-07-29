#!/usr/bin/env fish

if test (count $argv) -eq 2
	set repo_path $argv[2]
	set author_name $argv[1]
else if test (count $argv) -eq 1
	echo "Missing repo (E.g → Usage $0 <author> <repo-path>)"
	echo Starting with default repo → (pwd)
	set author_name $argv[1]
	set repo_path (pwd)
else
	echo Starting with default repo → (pwd)
	echo "No author mentions(E.g → Usage $0 <author> <repo-path>)"
	echo Using all authors
	set repo_path (pwd)
	cd $repo_path
	set author_name (git log --format='%aN' | sort -u)
end


if not test -d $repo_path
	echo "Directory does not exists $repo_path"
	exit 2
end

cd $repo_path

function print_insert
	
	set total_insert (git log --author="$argv[1]" --pretty=tformat: --numstat | awk '{ add += $1 } END { print add }')
	
	if test -z "$total_insert"
		set total_insert 0
	end
	
	echo "Total insertions by '$argv[1]' → $total_insert"
end

for author in $author_name
	print_insert $author
end
